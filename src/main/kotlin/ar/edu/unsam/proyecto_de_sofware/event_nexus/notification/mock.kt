package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import org.springframework.stereotype.Component
import java.util.concurrent.CopyOnWriteArrayList
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap


interface CreatedEventListener {
    fun onCreated(event: Event)
}

@Component
class EventoCreadoSubject {
    private val listeners = CopyOnWriteArrayList<CreatedEventListener>()

    fun subscribe(listener: CreatedEventListener) {
        listeners.add(listener)
    }

    fun unsubscribe(listener: CreatedEventListener) {
        listeners.remove(listener)
    }

    fun notify(event: Event) {
        listeners.forEach { it.onCreated(event) }
    }

}




@Component
class SseNotificationService(
    private val objectMapper: ObjectMapper
) : CreatedEventListener {

    private val emitters = ConcurrentHashMap<String, SseEmitter>()

    fun agregarConexion(userId: String, emitter: SseEmitter) {
        emitters[userId] = emitter
        emitter.onCompletion { emitters.remove(userId) }
        emitter.onTimeout { emitters.remove(userId) }
        emitter.onError { emitters.remove(userId) }

        sendHeartbeat(userId)
    }

    override fun onCreated(evento: Event) {
        // Lógica para determinar qué usuarios deben recibir esta notificación
        emitters.forEach { (userId, emitter) ->
            // Aquí podrías verificar si el usuario con userId debe recibir la notificación
            try {
                val data = mapOf("tipo" to "nuevo-evento", "payload" to evento)
                emitter.send(SseEmitter.event().data(objectMapper.writeValueAsString(data)))
            } catch (e: Exception) {
                emitters.remove(userId)
                println("Error al enviar SSE a $userId: ${e.message}")
            }
        }
    }

    @Scheduled(fixedRate = 5000) // Enviar heartbeat cada 30 segundos
    fun sendHeartbeats() {
        emitters.forEach { (userId, emitter) ->
            sendHeartbeat(userId)
        }
    }
    private fun sendHeartbeat(userId: String) {
        val emitter = emitters[userId] ?: return
        try {
            emitter.send(SseEmitter.event().name("heartbeat").data(objectMapper.writeValueAsString(LocalDateTime.now().toString())))
        } catch (e: Exception) {
            emitters.remove(userId)
            println("Error al enviar hearbeat para userId $userId: ${e.message}")
            emitter.completeWithError(e)
        }
    }

}

@Component
class NotificationInitializer(
    private val eventoCreadoSubject: EventoCreadoSubject,
    private val sseNotificationService: SseNotificationService
) {
    @PostConstruct
    fun subscribeListeners() {
        eventoCreadoSubject.subscribe(sseNotificationService)
        println("SseNotificationService suscrito a EventoCreadoSubject")
    }
}
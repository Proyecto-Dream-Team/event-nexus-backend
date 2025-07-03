package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.EventNotification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.toEventCreatorNofitication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.toEventNotification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.toNotificationDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener.CreatedEventListener
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.NotificationRepository
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

@Component
class SseNotificationService(
    private val objectMapper: ObjectMapper,
    val notificationRepository: NotificationRepository
) : CreatedEventListener {

    private val emitters: MutableMap<String, SseEmitter> = mutableMapOf()
    fun connected(key:String):Boolean{
        return emitters[key] != null
    }
    fun removeConnection(userId: String) {
        emitters.remove(userId)
    }

    fun agregarConexion(userId: String, emitter: SseEmitter) {
//        if(emitters[userId] != null){
            emitters[userId] = emitter
//        }
        emitter.onCompletion { emitters.remove(userId) }
        emitter.onTimeout { emitters.remove(userId) }
        emitter.onError { emitters.remove(userId) }
    }

    override fun onCreated(event: Event, notification:Notification) {
        emitters.forEach { (userId, emitter) ->
            try {
                val data2 = mapOf("type" to "new-notification", "payload" to toNotificationDTO(notification))
                emitter.send(SseEmitter.event().data(objectMapper.writeValueAsString(data2)))
            } catch (e: Exception) {
                emitters.remove(userId)
                println("Error al enviar SSE a $userId: ${e.message}")
            }
        }
    }

    //    Scheduled hace el trabajo de ejecutarse cada 30 segundos, y envia informacion a las conexiones de los emitters
    @Scheduled(fixedRate = 5000) // Enviar heartbeat cada 30 segundos
    fun sendHeartbeats() {
        emitters.forEach { (userId, _) ->
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

    override fun notifyJoined(event: Event, notification: Notification) {
        emitters.forEach { (userId, emitter) ->
            try {
                if(notification.listeners.map { it.id }.contains(userId.toLong())){
                    val data2 = mapOf("type" to "new-notification", "payload" to toNotificationDTO(notification))
                    emitter.send(SseEmitter.event().data(objectMapper.writeValueAsString(data2)))
                }
            } catch (e: Exception) {
                emitters.remove(userId)
                println("Error al enviar SSE a $userId: ${e.message}")
            }
        }
    }



}
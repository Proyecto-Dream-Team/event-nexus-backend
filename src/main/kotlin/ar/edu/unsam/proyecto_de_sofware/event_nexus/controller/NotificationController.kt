package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller


import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.CreatedEventObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.SseNotificationService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.TimeUnit

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/notification")
class NotificationController(
    private val sseNotificationService: SseNotificationService,
    private val createdEventObserver: CreatedEventObserver
) {

    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(@RequestParam userId: String): SseEmitter {
        val emitter = SseEmitter(TimeUnit.MINUTES.toMillis(5)) // Timeout de la conexión
        createdEventObserver.subscribe(sseNotificationService)
        sseNotificationService.agregarConexion(userId, emitter)
        return emitter
    }
}
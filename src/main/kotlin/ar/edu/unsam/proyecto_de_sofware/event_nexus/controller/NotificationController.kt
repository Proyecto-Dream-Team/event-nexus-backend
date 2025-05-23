package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller


import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.NotificationDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.toNotificationDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.CreatedEventObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.NotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.SseNotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.TimeUnit

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173", "http://localhost:3001"])
@RestController
@RequestMapping("/notification")
class NotificationController(
    private val sseNotificationService: SseNotificationService,
    private val createdEventObserver: CreatedEventObserver,
    val notificationService: NotificationService,
    val userService: UserService
) {

    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(@RequestParam userId: String): SseEmitter {
        lateinit var emitter: SseEmitter
        emitter = SseEmitter(TimeUnit.MINUTES.toMillis(5)) // Timeout de la conexión
        if(!sseNotificationService.connected(userId)){
            sseNotificationService.agregarConexion(userId, emitter)
            return emitter
        }
        emitter = SseEmitter(TimeUnit.SECONDS.toMillis(1))
        return emitter
    }
    @GetMapping("/{employeeId}")
    fun getNotifications(@PathVariable employeeId: Long): List<NotificationDTO>{
        return notificationService.getNotificationsByCreator(employeeId).map{ toNotificationDTO(it) }
    }
}
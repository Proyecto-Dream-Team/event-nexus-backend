package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller


import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.NotificationDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.toNotificationDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.CreatedEventObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.security.JwtUtil
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.NotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.SseNotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.TimeUnit

@CrossOrigin(origins = ["http://localhost:8080", "http://localhost:5173", "http://localhost:3001"])
@RestController
@RequestMapping("/notification")
class NotificationController(
    private val sseNotificationService: SseNotificationService,
    private val createdEventObserver: CreatedEventObserver,
    val notificationService: NotificationService,
    val userService: UserService,
    val jwtUtil: JwtUtil
) {

    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(request: HttpServletRequest): SseEmitter {
        val idToken = jwtUtil.getId(request)
        var emitter: SseEmitter = SseEmitter(TimeUnit.MINUTES.toMillis(5))
        if(!sseNotificationService.connected(idToken.toString())){
            sseNotificationService.agregarConexion(idToken.toString(), emitter)
            return emitter
        }
        sseNotificationService.removeConnection(idToken.toString())
        sseNotificationService.agregarConexion(idToken.toString(), emitter)
        return emitter
    }

    @GetMapping("/employee")
    fun getNotifications(request: HttpServletRequest): List<NotificationDTO>{
        val idToken = jwtUtil.getId(request)
        return notificationService.getNotificationsByCreator(idToken).map{ toNotificationDTO(it) }
    }
}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller


import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.NotificationDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.toNotificationDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.JoinLeaveObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.security.JwtUtil
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.NotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.SseNotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
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
    val notificationService: NotificationService,
    val jwtUtil: JwtUtil
) {

    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(@RequestParam employeeId:Long, request: HttpServletRequest): SseEmitter {
        var emitter: SseEmitter = SseEmitter(TimeUnit.MINUTES.toMillis(5))
        if(!sseNotificationService.connected(employeeId.toString())){
            sseNotificationService.agregarConexion(employeeId.toString(), emitter)
            return emitter
        }
        sseNotificationService.removeConnection(employeeId.toString())
        sseNotificationService.agregarConexion(employeeId.toString(), emitter)
        return emitter
    }

    @GetMapping("/employee")
    fun getNotifications(request: HttpServletRequest): List<NotificationDTO>{
        val idToken = jwtUtil.getId(request)
        return notificationService.getNotificationsByCreator(idToken).map{ toNotificationDTO(it) }
    }
}
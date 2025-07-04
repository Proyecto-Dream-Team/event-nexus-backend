package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification

import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.JoinLeaveObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.NewDirectiveObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.NewEventObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.SseNotificationService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
//
@Component
class NotificationInitializer(
    private val joinLeaveObserver: JoinLeaveObserver,
    private val newEventObserver: NewEventObserver,
    private val newDirectiveObserver: NewDirectiveObserver,
    private val sseNotificationService: SseNotificationService
) {
    @PostConstruct
    fun subscribeListeners() {
        joinLeaveObserver.subscribe(sseNotificationService)
        newEventObserver.subscribe(sseNotificationService)
        newDirectiveObserver.subscribe(sseNotificationService)
        println("SseNotificationService suscrito a EventoCreadoSubject")
    }
}
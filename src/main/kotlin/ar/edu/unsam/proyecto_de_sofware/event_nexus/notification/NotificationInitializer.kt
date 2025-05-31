package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification

import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.CreatedEventObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.SseNotificationService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class NotificationInitializer(
    private val createdEventObserver: CreatedEventObserver,
    private val sseNotificationService: SseNotificationService
) {
    @PostConstruct
    fun subscribeListeners() {
        createdEventObserver.subscribe(sseNotificationService)
        println("SseNotificationService suscrito a EventoCreadoSubject")
    }
}
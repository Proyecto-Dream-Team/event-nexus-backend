package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener.NewEventListener
import org.springframework.stereotype.Component
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.collections.forEach

@Component
class NewEventObserver{

    val listeners = CopyOnWriteArrayList<NewEventListener>()

    fun subscribe(listener: NewEventListener) {
        listeners.add(listener)
    }

    fun notifyNewEvent(event: Event, notification: Notification){
        listeners.forEach { it.notifyCreatedEvent(event, notification) }
    }
}

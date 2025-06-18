package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener.CreatedEventListener
import org.springframework.stereotype.Component

@Component
class CreatedEventObserver: GenericObserver<Event, CreatedEventListener>(){

    fun notifyLeaveOrJoined(event:Event, notification: Notification, joined:Boolean){
        listeners.forEach { it.notifyJoined(event, notification) }
    }
}
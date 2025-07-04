package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener.JoinLeaveEventListener
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener.NewEventListener
import org.springframework.stereotype.Component
import java.util.concurrent.CopyOnWriteArrayList

@Component
class JoinLeaveObserver{

    val listeners = CopyOnWriteArrayList<JoinLeaveEventListener>()

    fun subscribe(listener: JoinLeaveEventListener) {
        listeners.add(listener)
    }

    fun notifyLeaveOrJoined(event:Event, notification: Notification, joined:Boolean){
        listeners.forEach { it.notifyJoined(event, notification) }
    }
}

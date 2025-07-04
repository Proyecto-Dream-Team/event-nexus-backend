package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification

interface JoinLeaveEventListener{

    fun notifyJoined(event:Event, notification: Notification)

}
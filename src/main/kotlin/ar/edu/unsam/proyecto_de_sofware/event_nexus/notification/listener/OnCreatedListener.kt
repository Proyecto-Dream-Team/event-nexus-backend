package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notifiable
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification

interface CreatedEntity<N: Notifiable>{
    fun onCreated(entity:N, notification: Notification)
}
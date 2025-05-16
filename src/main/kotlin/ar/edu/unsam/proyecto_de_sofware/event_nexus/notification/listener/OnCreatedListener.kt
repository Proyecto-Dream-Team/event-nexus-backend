package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notifiable

interface CreatedEntity<N: Notifiable>{
    fun onCreated(entity:N)
}
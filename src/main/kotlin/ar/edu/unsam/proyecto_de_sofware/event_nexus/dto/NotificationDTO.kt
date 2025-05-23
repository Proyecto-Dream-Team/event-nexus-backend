package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification

data class NotificationDTO(
    val id:Long,
    val title:String,
    val date:String,
    val readed:Boolean
)

fun toNotificationDTO(notification: Notification): NotificationDTO{
    return NotificationDTO(
        id = notification.id!!,
        title = notification.title,
        date = notification.date.toString(),
        readed = notification.readed

    )
}
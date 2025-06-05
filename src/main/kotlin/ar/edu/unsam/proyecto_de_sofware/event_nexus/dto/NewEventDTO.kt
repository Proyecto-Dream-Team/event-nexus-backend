package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventType
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import java.time.LocalDateTime

data class NewEventDTO(
    val id: Long,
    val creatorId: Long,
    val participantsIds: MutableSet<Long>,
    val date: LocalDateTime,
    val name: String,
    val description: String,
    val eventType: EventType
)

fun fromEventDTOtoEvent(creatorEmployee: Employee, participantsEmployees: List<Employee>, newEventDTO: NewEventDTO):Event{
    return Event().apply {
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        title = newEventDTO.name
        description = newEventDTO.description
        expirationDate = newEventDTO.date
        type = newEventDTO.eventType
    }
}
data class EventParticipantDTO(
    val id:Long,
    val name:String,
    val image:String
)
data class ShowEventDTO(
    val id: Long?,
    val creatorName: String,
    val creatorImage: String,
    val creatorId: Long?,
    val dateFinished: LocalDateTime,
    val title: String,
    val description: String,
    val isActive: Boolean,
    val participants: List<EventParticipantDTO>,
    val type: EventType
)

fun Event.showEventDTO(): ShowEventDTO {
    return ShowEventDTO(
        id = id,
        creatorName = creator.name + " " + creator.lastname,
        creatorImage = creator.image,
        creatorId = creator.id,
        dateFinished = expirationDate,
        title = title,
        description = description,
        isActive = isPending(),
        participants = participants.map { it.toEventParticipantDTO() },
        type = type
    )
}

data class EventNotification(
    val id:Long,
    val eventId: Long,
    val from:String,
    val date: LocalDateTime,
    val shortText:String
)

fun toEventNotification(event:Event, notification: Notification): EventNotification{
    return EventNotification(
        id = notification.id!!,
        eventId = event.id!!,
        from = event.creator.fullName(),
        date = event.creationDate,
        shortText = event.title
    )
}


fun toEventCreatorNofitication(event:Event, notification: Notification, joined:Boolean): EventNotification{
    lateinit var text: String
    text = if(joined) "Alguien se unio al evento"
    else "Alguien abandono el evento"
    return EventNotification(
        id = notification.id!!,
        eventId = event.id!!,
        from = notification.creator.fullName(),
        date = event.creationDate,
        shortText = text
    )
}


data class ResponseEntityDTO(
    val responseMessage:String,
    val responseBody:List<EventParticipantDTO>,
)
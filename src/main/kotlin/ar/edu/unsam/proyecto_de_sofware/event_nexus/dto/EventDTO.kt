package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventType
import java.time.LocalDateTime

data class EventDTO(
    val id: Long,
    val creatorId: Long,
    val participantsIds: MutableSet<Long>,
    val date: LocalDateTime,
    val name: String,
    val description: String,
    val eventType: EventType
)

data class ShowEventDTO(
    val id: Long?,
    val creatorName: String,
    val creatorImage: String,
    val creatorId: Long?,
    val dateFinished: LocalDateTime,
    val title: String,
    val description: String,
    val numberOfParticipants: Int,
    val participantsIds: Array<Long?>,
    val type: EventType
)

fun Event.showEventDTO(): ShowEventDTO {
    return ShowEventDTO(
        id = id,
        creatorName = creator.name + " " + creator.lastname,
        creatorImage = creator.image,
        creatorId = creator.id,
        dateFinished = dateFinished,
        title = title,
        description = description,
        numberOfParticipants = participants.size + 1,
        participantsIds = participants.map { it.id }.toTypedArray(),
        type = type
    )
}

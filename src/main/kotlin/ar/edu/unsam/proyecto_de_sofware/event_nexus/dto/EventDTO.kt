package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import java.time.LocalDateTime

data class EventDTO(
    val creatorId: Long,
    val participantsIds: MutableSet<Long>,
    val date: LocalDateTime,
    val name: String,
    val description: String
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
    val numberOfParticipants: Int,
    val participantsIds: List<Long?>
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
        isActive = isPending(),
        numberOfParticipants = participants.size + 1,
        participantsIds = participants.map { it.id }
    )
}

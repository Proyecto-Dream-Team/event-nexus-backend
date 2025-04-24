package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import java.time.LocalDate
import java.time.LocalDateTime

data class EventDTO(
    val creatorId:Long,
    val participantsIds:List<Long>,
    val date: LocalDateTime,
    val name:String
)

data class ShowEventDTO(
    val id: Long?,
    val creatorId: Long?,
    val participantsIds: List<Long?>,
    val date: LocalDateTime,
    val dateFinished: LocalDateTime,
    val name: String,
    val description: String,
    val isPending: Boolean
)

fun Event.showEventDTO(): ShowEventDTO {
    return ShowEventDTO(
        id = id,
        creatorId = creator.id,
        participantsIds = participants.map { it.id },
        date = date,
        dateFinished = dateFinished,
        name = name,
        description = description,
        isPending = isPending()

    )
}

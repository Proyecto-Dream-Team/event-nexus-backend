package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.context.annotation.Description
import java.time.LocalDateTime
import kotlin.time.Duration

data class EventDTO(
    val id: Long,
    val date: LocalDateTime,
    val dateFinished: LocalDateTime,
    val duration: Int,
    val guests: List<EmployeeDTO>,
    val description: String
)

fun Event.toDTO() = EventDTO(
    id = id!!,
    date = date,
    dateFinished = dateFinished,
    duration = duration,
    guests = guests.map { it.toDTO() },
    description = description
)

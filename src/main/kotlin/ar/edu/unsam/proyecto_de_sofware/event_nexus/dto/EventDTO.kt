package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class EventDTO(
    val creatorId:Long,
    val participantsIds:List<Long>,
    val date: LocalDateTime,
    val name:String
)
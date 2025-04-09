package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.User

data class HomeDTO (
    val id: Int
){}

fun User.toHomeDTO() = HomeDTO(
    id = id
)
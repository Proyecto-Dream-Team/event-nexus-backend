package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.User

data class HeaderDTO (
    val id: Int,
    val img: String,
    val name: String,
    val lastname: String
){}

fun User.toHeaderDTO() = HeaderDTO(
    id = id,
    name = name,
    lastname = lastname,
    img = img

)
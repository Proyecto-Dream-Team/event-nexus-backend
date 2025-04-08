package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.User

data class ProfileDTO(
    val id: Int,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val address: String
){}

fun User.toProfileDTO() = ProfileDTO(
    id = id,
    name = name,
    lastName = lastname,
    phone = phone,
    email = email,
    address = address
)
package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee

data class ProfileDTO(
    val id: Long,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val address: String
){}


fun Employee.toProfileDTO():ProfileDTO{

    val notNullId = requireNotNull(id)

    return ProfileDTO(
        id = notNullId,
        name = name,
        lastName = lastname,
        phone = phone,
        email = email,
        address = address
    )
}
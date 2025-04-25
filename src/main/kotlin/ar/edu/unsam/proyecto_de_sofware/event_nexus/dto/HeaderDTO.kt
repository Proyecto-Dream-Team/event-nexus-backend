package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee

data class HeaderDTO (
    val id: Long,
    val name: String,
    val lastname: String
){}

fun Employee.toHeaderDTO():HeaderDTO{

    val notNullId = requireNotNull(id)

    return HeaderDTO(
        id = notNullId,
        name = name,
        lastname = lastname,
    )
}

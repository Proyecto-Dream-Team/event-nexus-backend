package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee

class EmployeeDTO(
    val id: Long,
    val name: String,
    val lastName: String
) {
}

fun Employee.toDTO() = EmployeeDTO(
    id = id!!,
    name = name,
    lastName = lastname
)
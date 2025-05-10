package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee

data class EmployeeDTO(
    val id: Long,
    val name: String,
    val lastname: String,
    val image: String
) {}

fun Employee.toEmployeeDTO() = EmployeeDTO(
    id = id!!,
    name = name,
    lastname = lastname,
    image = image
)

data class UserCreateDTO(
    val name: String,
    val lastName: String,
    val email: String,
    val module: List<Module>,
    val
)

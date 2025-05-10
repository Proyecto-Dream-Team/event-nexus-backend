package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission

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
    val username: String,
    val password: String,
    val name: String,
    val lastName: String,
    val email: String,
    val module: List<Module>,
    val permissions: List<Permission>,
    val role: Role
){
    fun toEmployee(): Employee{
        return Employee().apply {
            name = this@UserCreateDTO.name
            lastname = lastName
            job = ""
            address = ""
            phone = ""
            email = this@UserCreateDTO.email
            permissions = this@UserCreateDTO.permissions.toMutableSet()
            credentials = Authentication().apply {
                email = this@UserCreateDTO.email
                username = this@UserCreateDTO.username
                password = this@UserCreateDTO.password
                role = this@UserCreateDTO.role
            }
        }

    }
}


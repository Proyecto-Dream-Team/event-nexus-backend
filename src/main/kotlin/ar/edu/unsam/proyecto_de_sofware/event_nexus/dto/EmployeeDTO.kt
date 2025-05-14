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
    val name: String,
    val lastName: String,
    val email: String,
    val permissions: List<Permission>,
    val role: Role,
    val address: String,
    val phone: String
){
    fun toEmployee(): Employee{
        return Employee().apply {
            name = this@UserCreateDTO.name
            lastname = lastName
            job = this@UserCreateDTO.role.name
            address = this@UserCreateDTO.address
            phone = this@UserCreateDTO.phone
            email = this@UserCreateDTO.email
            permissions = this@UserCreateDTO.permissions.toMutableSet()
            credentials = Authentication().apply {
                email = this@UserCreateDTO.email
                username = "back"
                password = "back"
                role = this@UserCreateDTO.role
            }
        }
    }
}

data class EditEmployeeDTO(
    val id: Long,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val address: String,
    val permissions: List<Permission>,
)


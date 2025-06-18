package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission

data class EmployeeDTO(
    val id: Long,
    val name: String,
    val lastname: String,
    val image: String,
    val rol: String
) {}

fun Employee.toEmployeeDTO() = EmployeeDTO(
    id = id!!,
    name = name,
    lastname = lastname,
    image = image,
    rol = job
)
fun Employee.toEventParticipantDTO() = EventParticipantDTO(
    id = id!!,
    name = name +" "+ lastname,
    image = image,
)

data class UserCreateDTO(
    val id: Long? = null,
    val name: String,
    val lastName: String,
    val email: String,
    val permissions: List<String>,
    val role: Role,
    val address: String,
    val phone: String
){
    fun toEmployee(): Employee{
        val domainPermissions:List<Permission> = Permission.entries.filter { permissions.contains(it.permissionName) }
        return Employee().apply {
            name = this@UserCreateDTO.name
            lastname = lastName
            job = this@UserCreateDTO.role.name
            address = this@UserCreateDTO.address
            phone = this@UserCreateDTO.phone
            email = this@UserCreateDTO.email
            permissions = domainPermissions.toMutableSet()
            credentials = Credentials().apply {
                email = this@UserCreateDTO.email
                username = "back"
                password = "back"
                role = this@UserCreateDTO.role
            }
        }
    }
}

fun Employee.toUserCreateDTO() = UserCreateDTO(
    id = this.id,
    name = this.name,
    address = this.address,
    email = this.email,
    lastName = this.lastname,
    permissions = this.permissions.map { it.permissionName },
    role = this.credentials.role,
    phone = this.phone
)

data class EditEmployeeDTO(
    val id: Long,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val address: String,
    val permissions: List<Permission>,
)


data class Availa(
    val id: Long,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val address: String,
    val permissions: List<Permission>,
)

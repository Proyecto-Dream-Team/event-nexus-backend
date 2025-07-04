package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission

data class ProfileDTO(
    val id: Long,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val address: String,
    val job: String,
    val permissons: List<String>
){

    fun toEmployee(): Employee{
        return Employee().apply {
            name = this@ProfileDTO.name
            lastname = this@ProfileDTO.lastName
            phone = this@ProfileDTO.phone
            email = this@ProfileDTO.email
            address = this@ProfileDTO.address
        }
    }
}




fun Employee.toProfileDTO(permisos : List<String>):ProfileDTO{

    val notNullId = requireNotNull(id)

    return ProfileDTO(
        id = notNullId,
        name = name,
        lastName = lastname,
        phone = phone,
        email = email,
        address = address,
        job = job,
        permissons = permisos.toList()
    )
}
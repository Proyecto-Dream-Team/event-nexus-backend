package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee

data class DataUpdateProfileDTO(
    val id: Long,
    var address: String,
    var phone: String,
    var email: String
)

//fun Employee.toDataUpdateProfileDTO() = DataUpdateProfileDTO(
//    address = address,
//    phone = phone,
//    email = email,
//    id = id
//)

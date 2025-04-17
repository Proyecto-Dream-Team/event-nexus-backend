package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

data class NewAccountRequest(val username:String, val password:String, val email:String, val jobName:String)

data class LoginRequest(val username:String, val password:String)

data class LoginResponse(val id:Long, val role:String, val img: ByteArray)
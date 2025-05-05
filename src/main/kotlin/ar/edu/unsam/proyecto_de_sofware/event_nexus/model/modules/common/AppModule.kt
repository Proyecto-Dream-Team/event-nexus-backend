package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common

import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.CommandNotAllowedException



abstract class AppModule(
    open val id: Int,
    open val name:String,
    open val image:String
){
    open lateinit var description:String
    fun checkPermission(employeePermmission: MutableSet<Permission>, permissionToCheck: Permission){
        if(employeePermmission.none{it == permissionToCheck}){
            throw CommandNotAllowedException(commandName = permissionToCheck.name)
        }
    }
}



package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common

import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.CommandNotAllowedException


abstract class ModuleCommand{

    abstract fun doExecute()
    abstract fun getClassName():String

    fun execute(userAllowedCommands: MutableSet<ModuleCommand>){
        checkPermissions(userAllowedCommands)
        doExecute()
    }

    private fun checkPermissions(userAllowedCommands: MutableSet<ModuleCommand>){
        if(this.notAllowedPermit(userAllowedCommands)){
            throw CommandNotAllowedException(this.getClassName())
        }
    }

    private fun notAllowedPermit(userAllowedCommands: MutableSet<ModuleCommand>):Boolean{
        val commandClass = this::class
        return userAllowedCommands.none { it::class == commandClass }

    }
}

package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common

import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.CommandNotAllowedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.ModuleNotAllowedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee


abstract class ModulePermission{

    abstract val module:AppModule

    fun moduleName():String = this.module::class.simpleName!!

    abstract fun getClassName():String

    fun execute(employee: Employee){
        checkModuleAccess(employeeModules = employee.modules)
        checkPermissions(employeeAllowedPermissions = employee.permissions)
    }

    private fun checkModuleAccess(employeeModules: MutableSet<AppModule>){
        if(this.notAllowedModule(employeeModules)){
            throw ModuleNotAllowedException(this.moduleName())
        }
    }

    private fun notAllowedModule(employeeModules: MutableSet<AppModule>):Boolean{
        return employeeModules.none { module:AppModule ->
            module::class == this.module::class
        }
    }

    private fun checkPermissions(employeeAllowedPermissions: MutableSet<ModulePermission>){
        if(this.notAllowedPermit(employeeAllowedPermissions)){
            throw CommandNotAllowedException(this.getClassName())
        }
    }

    private fun notAllowedPermit(userAllowedCommands: MutableSet<ModulePermission>):Boolean{
        val commandClass = this::class
        return userAllowedCommands.none { it::class == commandClass }

    }
}

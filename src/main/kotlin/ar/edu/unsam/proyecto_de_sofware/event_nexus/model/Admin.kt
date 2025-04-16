package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import jakarta.persistence.Entity

@Entity
class Admin():Employee() {
    fun createAccount(username:String, password:String, email:String,  role:Role): Authentication{
        val account = Authentication()
        account.username = username
        account.password = password
        account.email = email
        account.role = role
        return account
    }

    fun addPermission(employee: Employee, permission: ModuleCommand){
        employee.permissions.add(permission)
    }

    fun addPermissions(employee: Employee, permissions: Set<ModuleCommand>){
        employee.permissions.addAll(permissions)
    }

    fun deletePermission(employee: Employee, permission: ModuleCommand){
        employee.permissions.remove(permission)
    }

    fun deletePermissions(employee: Employee, permissions: Set<ModuleCommand>){
        employee.permissions.removeAll(permissions)
    }
}
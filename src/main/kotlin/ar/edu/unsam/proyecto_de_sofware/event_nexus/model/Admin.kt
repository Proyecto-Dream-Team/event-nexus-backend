package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
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

    fun addPermission(employee: Employee, permission: Permission){
        if(employee.permissions.contains(permission)) throw BusinessException("Ya tiene el permiso $permission")
        employee.permissions.add(permission)
    }

    fun addPermissions(employee: Employee, permissions: Set<Permission>){
        val nonGrantedPermissions: List<Permission> = permissions.filter { !employee.permissions.contains(it) }
        if(nonGrantedPermissions.isEmpty()) throw BusinessException("Ya tiene los permisos")
        employee.permissions.addAll(nonGrantedPermissions)
    }

    fun deletePermission(employee: Employee, permission: Permission){
        if(!employee.permissions.contains(permission)) throw BusinessException("No tiene el permiso $permission. No se puede eliminar")
        employee.permissions.remove(permission)
    }

    fun deletePermissions(employee: Employee, permissions: Set<Permission>){
        val grantedPermissions: List<Permission> = permissions.filter { employee.permissions.contains(it) }
        if(grantedPermissions.isEmpty()) throw BusinessException("No tiene los permisos $permissions. No se puede eliminarlos")
        employee.permissions.removeAll(permissions)
    }
}
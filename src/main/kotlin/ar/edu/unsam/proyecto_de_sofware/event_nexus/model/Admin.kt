package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand

class Admin():Person {

    override var name: String = ""
    override var lastname: String = ""
    override val job: String = Role.ADMIN.jobName
    override var active: Boolean = true
    override var permissions: MutableSet<ModuleCommand> = Role.EMPLOYEE_WATCHER.permissions.toMutableSet()

    fun createAccount(username:String, password:String, role:Role): Authentication{
        val account = Authentication()
        account.username = username
        account.password = password
        account.role = role.jobName
        return account
    }

    fun addPermission(person: Person, permission: ModuleCommand){
        person.permissions.add(permission)
    }

    fun addPermissions(person: Person, permissions: Set<ModuleCommand>){
        person.permissions.addAll(permissions)
    }

    fun deletePermission(person: Person, permission: ModuleCommand){
        person.permissions.remove(permission)
    }

    fun deletePermissions(person: Person, permissions: Set<ModuleCommand>){
        person.permissions.removeAll(permissions)
    }

    override fun executeModuleAction(command: ModuleCommand) {
        command.execute(this.permissions)
    }
}
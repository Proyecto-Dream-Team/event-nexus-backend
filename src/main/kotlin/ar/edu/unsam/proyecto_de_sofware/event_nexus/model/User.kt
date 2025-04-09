package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AvaliableInstance

class User(override var id: Int):Person, AvaliableInstance {
    override var name: String = ""
    override var lastname: String = ""
    override val job: String = Role.EMPLOYEE_WATCHER.jobName
    override var active: Boolean = true
    override var permissions: MutableSet<ModuleCommand> = Role.EMPLOYEE_WATCHER.permissions.toMutableSet()
    override fun executeModuleAction(command: ModuleCommand) {
        command.execute(this.permissions)
    }
}




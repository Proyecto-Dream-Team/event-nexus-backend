package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AvaliableInstance

class User():Person, AvaliableInstance {
    override var id: Int = 0
    override var name: String = ""
    override var lastname: String = ""
    override val job: String = Role.EMPLOYEE_WATCHER.jobName
    override var active: Boolean = true
    override var address: String = ""
    override var phone: String = ""
    override var email: String = ""
    override var permissions: MutableSet<ModuleCommand> = Role.EMPLOYEE_WATCHER.permissions.toMutableSet()
    var modules: List<AppModule> = listOf()


    override fun executeModuleAction(command: ModuleCommand) {
        command.execute(this.permissions)
    }
}




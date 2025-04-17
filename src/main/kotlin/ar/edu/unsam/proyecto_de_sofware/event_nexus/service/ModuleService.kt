package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.CommandRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ModuleService(
    @Autowired val userService: UserService,
    @Autowired val commandRepo: CommandRepository,
    val moduleRepo: ModuleRepository
) {
    fun getAll(user: Employee): List<AppModule> {
        return moduleRepo.
    }

    fun mock(id: Long): List<ModuleCommand> {
        val employeeCommands:List<ModuleCommand> = commandRepo.findEmployeeCommandsByID(id)
        return employeeCommands
    }

    fun get()

}
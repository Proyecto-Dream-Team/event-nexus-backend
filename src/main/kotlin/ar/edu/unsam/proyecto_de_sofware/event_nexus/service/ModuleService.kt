package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.CommandRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ModuleService(
    @Autowired val userService: UserService,
    @Autowired val commandRepo: CommandRepository
) {
    fun getAll(id: Long): List<AppModule> {
        val user = userService.getByID(id)
        val modules: List<AppModule> = listOf()
        return modules
    }

    fun mock(id: Long): List<ModuleCommand> {
        val employeeCommands:List<ModuleCommand> = commandRepo.findEmployeeCommandsByID(id)
        return employeeCommands
    }

}
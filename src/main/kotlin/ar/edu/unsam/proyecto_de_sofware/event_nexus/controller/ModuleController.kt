package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports.RepportModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.ModuleService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173", "http://localhost:3001"])
@RestController
@RequestMapping()
class ModuleController(val moduleService: ModuleService, val userService: UserService) {

    @GetMapping("/module")
    fun modules():List<AppModule>{
        return moduleService.all()
    }

    @GetMapping("/module/{id}")
    fun employeeModules(@PathVariable id: Long):List<AppModule>{
        val employee = userService.getByID(id)

        return if(employee.credentials.role == Role.ADMIN) moduleService.all() else listOf(EventModule(),
            DirectiveModule())
    }

}
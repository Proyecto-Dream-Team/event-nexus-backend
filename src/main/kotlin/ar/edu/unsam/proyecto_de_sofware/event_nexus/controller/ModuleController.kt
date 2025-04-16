package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports.RepportModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.space_reservations.SpaceReservationsModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.ModuleService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/module")
class ModuleController(val moduleService: ModuleService) {
    @GetMapping("/{id}/all")
    fun get(@PathVariable id: Long): Set<ModuleDTO>{
        val employeeCommands:List<ModuleCommand> = moduleService.mock(id)
        return employeeCommands.map { ModuleDTO(it.module.id, it.module.name, it.module.image) }.toSet()
    }

}
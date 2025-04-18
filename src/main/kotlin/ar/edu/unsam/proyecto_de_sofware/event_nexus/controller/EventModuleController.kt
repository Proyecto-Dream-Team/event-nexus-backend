package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ModuleDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.EventService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.ModuleService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/eventmodule")
class EventModuleController(val eventService: EventService, val userService: UserService) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): String{
        val user = userService.getByID(id)
//        return eventService.getAllEvent(user)
        return "ARREGLAR"
    }

}
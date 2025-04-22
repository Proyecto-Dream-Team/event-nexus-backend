package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.EventService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/events")
class EventModuleController(val eventService: EventService, val userService: UserService) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): List<Event> {
        return eventService.getAllEvents(id)
    }

}
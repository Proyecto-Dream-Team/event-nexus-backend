package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.User
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CancelEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CreateEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.MockService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


//@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
class MockController(val mockService: MockService) {
    @GetMapping("/mock")
    fun createEvent():String{
        val admin = mockService.getAdmin(3)
        val user = mockService.getUser(1)

        return "Admin es del tipo ${admin!!::class}. User es del tipo ${user!!::class}"
    }
}


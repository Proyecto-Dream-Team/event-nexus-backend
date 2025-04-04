package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.CreateEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.User
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


//@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
class MockController {
    @GetMapping("/mock")
    fun createEvent():String{
        val user = User()
        val createEventCommand = CreateEvent()
        user.allowedModuleCommand.add(createEventCommand)
        user.setModuleAction(createEventCommand)
        user.executeModuleAction()
        return "Ejecucion exitora"
    }
}


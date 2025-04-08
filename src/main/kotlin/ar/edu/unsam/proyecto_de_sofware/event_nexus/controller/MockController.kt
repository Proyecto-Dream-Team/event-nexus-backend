package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.User
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CreateEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


//@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
class MockController {
    @GetMapping("/mock")
    fun createEvent():String{
//        val admin = Admin()
//        val user = User()
//        admin.addPermission(user, CreateEvent())
//        user.executeModuleAction(CreateEvent())
          return "Ejecucion exitora"
    }
}


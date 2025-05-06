package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.MockService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


//@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
class MockController(val mockService: MockService) {


    @GetMapping("/mock")
    fun mock():String{
        val admin = mockService.getAdmin(1)
        val user = mockService.getUser(3)

        return "Admin es del tipo ${admin!!::class}. User es del tipo ${user!!::class}. Admin name: ${admin.name}"
    }


    @GetMapping("/mock2")
    fun mock2(): List<Permission>{
        return Permission.entries.toList()
    }



}


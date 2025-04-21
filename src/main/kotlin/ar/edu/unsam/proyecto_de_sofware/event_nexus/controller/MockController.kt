package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CreateEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.MockService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


//@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
class MockController(val mockService: MockService) {

    @GetMapping("/mock/module")
    fun modules():List<AppModule>{
        return mockService.modulosAll()
    }

    @GetMapping("/mock/module/{id}")
    fun employeeModules(@PathVariable id: Long):List<AppModule>{
        return mockService.employeeModulosById(id)
    }

    @GetMapping("/mock/event")
    fun eventos():List<Event>{
        return mockService.eventos()
    }

    @GetMapping("/mock/event/{id}/created")
    fun eventosCreados(@PathVariable id: Long):List<Event>{
        return mockService.employeeCreatedEvents(id)
    }

    @GetMapping("/mock/event/{id}/invited")
    fun eventosInvitados(@PathVariable id: Long):List<Event>{
        return mockService.employeeInvitedEvents(id)
    }


}


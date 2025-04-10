package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports.RepportModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.space_reservations.SpaceReservationsModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import org.springframework.web.bind.annotation.*

//@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/module")
class ModuleController(val authService: AuthService) {
    val modules:List<AppModule> = listOf(EventModule(), DirectiveModule(), RepportModule(), SpaceReservationsModule())

//    @GetMapping("/avaliable/{id}")
//    fun get(@PathVariable id: Int):ResponseEntity<String>{
//        if(role!="admin") throw BusinessException("Solo los admin pueden crear")
//        authService.createAccount(newAccountRequest)
//        return ResponseEntity.ok().body("Account succesfully created!")
//    }

    @GetMapping("/all")
    fun get():List<ModuleDTO>{
        return modules.map { toModuleDTO(it) }
    }

}
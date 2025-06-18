package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.security.JwtUtil
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.ModuleService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8080", "http://localhost:5173", "http://localhost:3001"])
@RestController
@RequestMapping()
class ModuleController(
    val moduleService: ModuleService,
    val userService: UserService,
    val jwtUtil: JwtUtil
) {

    @GetMapping("/module")
    fun employeeModules(request: HttpServletRequest):List<AppModule>{
        val idToken = jwtUtil.getId(request)
        val employee = userService.getByID(idToken)

        return if(employee.credentials.role == Role.ADMIN) moduleService.all() else listOf(EventModule(),
            DirectiveModule())
    }

}
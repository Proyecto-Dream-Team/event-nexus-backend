package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.HomeDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ProfileDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.toHomeDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.toProfileDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import jakarta.websocket.server.PathParam
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

//    @GetMapping("/home/{id}")
//    fun dataHome(@PathVariable id: Int): HomeDTO {
//        return userService.getUser(id).toHomeDTO()
//    }
//
//    @GetMapping("/profile/{id}")
//    fun dataProfile(@PathVariable id: Int): ProfileDTO {
//        return userService.getUser(id).toProfileDTO()
//    }
    @GetMapping("/header/{id}")
    fun dataHome(@PathVariable id: Int): HeaderDTO {
        return userService.getUser(id).toHeaderDTO()
    }

    @GetMapping("/profile/{id}")
    fun dataProfile(@PathVariable id: Int): ProfileDTO {
        return userService.getUser(id).toProfileDTO()
    }
}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import jakarta.websocket.server.PathParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @GetMapping("/home/{id}")
    fun homeModules(@PathVariable id: Long): Employee {
        return userService.getByID(id)
    }

    @GetMapping("/header/{id}")
    fun dataHome(@PathVariable id: Long): HeaderDTO {
        return userService.getByID(id).toHeaderDTO()
    }

    @GetMapping("/profile/{id}")
    fun dataProfile(@PathVariable id: Long): ProfileDTO {
        return userService.getByID(id).toProfileDTO()
    }

    @PutMapping("profile")
    fun profileUpdate(@RequestBody dataUpdateProfileDTO: DataUpdateProfileDTO): ResponseEntity<String>{
        return userService.updateProfile(dataUpdateProfileDTO)
    }

    @PutMapping("/img")
    fun changeImg(@RequestBody imgDTO: ImgDTO): ResponseEntity<String>{
        val base64Data = imgDTO.img.removePrefix("data:image/jpeg;base64,")
        val imageBytes = Base64.getDecoder().decode(base64Data)
        val user = userService.getByID(imgDTO.id)
        return userService.changeImg(user, imageBytes)
    }
}
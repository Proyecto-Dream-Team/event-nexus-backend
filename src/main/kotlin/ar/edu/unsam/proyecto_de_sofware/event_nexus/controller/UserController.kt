package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.PermissionType
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import jakarta.websocket.server.PathParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @GetMapping("/detail/{id}")
    fun getEmployee(@PathVariable id: Long): UserCreateDTO {
        return userService.getByID(id).toUserCreateDTO()
    }

    @PutMapping("profile")
    fun profileUpdate(@RequestBody dataUpdateProfileDTO: DataUpdateProfileDTO): ResponseEntity<String>{
        try{
            userService.updateProfile(dataUpdateProfileDTO)
        }catch (error: DataBaseNotModifiedException){
            ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(error.message)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Actualizacion exitosa!")
    }

    @PutMapping("/img")
    fun changeImg(@RequestBody imgDTO: ImgDTO): ResponseEntity<String>{
        val user = userService.getByID(imgDTO.id)
        try {
            userService.changeImg(user, imgDTO.img)
        }catch (error: DataBaseNotModifiedException){
            return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(error.message)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Actualizacion exitosa!")
    }

    @GetMapping("/{id}/permissions/{permissionType}")
    fun getPermissions(@PathVariable id: Long, @PathVariable permissionType: PermissionType): List<String>{
        return userService.gerPermissions(id, permissionType).map { it.permissionName }
    }

    @GetMapping( )
    fun search(@RequestParam search: String): List<EmployeeDTO>{
        return userService.findBySearch(search).map { it.toEmployeeDTO() }
    }

}
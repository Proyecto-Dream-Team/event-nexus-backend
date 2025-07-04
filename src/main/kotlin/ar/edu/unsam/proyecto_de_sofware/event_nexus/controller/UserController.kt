package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.PermissionType
import ar.edu.unsam.proyecto_de_sofware.event_nexus.security.JwtUtil
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import jakarta.servlet.http.HttpServletRequest
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

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173", "http://localhost:3001"])
@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    val jwtUtil: JwtUtil
) {

    @GetMapping("/home")
    fun homeModules(request: HttpServletRequest): Employee {
        val idToken = jwtUtil.getId(request)
        return userService.getByID(idToken)
    }

    @GetMapping("/header")
    fun dataHome(request: HttpServletRequest): HeaderDTO {
        val idToken = jwtUtil.getId(request)
        return userService.getByID(idToken).toHeaderDTO()
    }

    @GetMapping("/profile")
    fun dataProfile(request: HttpServletRequest): ProfileDTO {
        val idToken = jwtUtil.getId(request)
        var uss = userService.getByID(idToken)
        var permision = uss.permissions.map { it.permissionName }.toList()

        return uss.toProfileDTO(permision)
    }

    @GetMapping("/detail")
    fun getEmployee(request: HttpServletRequest): UserCreateDTO {
        val idToken = jwtUtil.getId(request)
        return userService.getByID(idToken).toUserCreateDTO()
    }

    @PutMapping("/profile")
    fun profileUpdate(@RequestBody dataUpdateProfileDTO: DataUpdateProfileDTO, request: HttpServletRequest): ResponseEntity<String>{
        val idToken = jwtUtil.getId(request)
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

    @GetMapping("/available")
    fun availableForEvent(@RequestBody dataUpdateProfileDTO: DataUpdateProfileDTO,request: HttpServletRequest): ResponseEntity<String>{
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
    fun changeImg(@RequestBody imgDTO: ImgDTO, request: HttpServletRequest): ResponseEntity<String>{
        val idToken = jwtUtil.getId(request)
        val user = userService.getByID(idToken)
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

    @GetMapping("/permissions/{permissionType}")
    fun getPermissions(@PathVariable permissionType: PermissionType, request: HttpServletRequest): List<String>{
        val idToken = jwtUtil.getId(request)
        return userService.gerPermissions(idToken, permissionType).map { it.permissionName }
    }

    @GetMapping()
    fun search(@RequestParam(required = false) searchInput: String?, request: HttpServletRequest): List<EmployeeDTO>{
        val idToken = jwtUtil.getId(request)
        if(searchInput != null) {
            return userService.findBySearch(searchInput).map { it.toEmployeeDTO() }
        }
        return userService.repoUser.findAll().map { it.toEmployeeDTO() }
    }

}
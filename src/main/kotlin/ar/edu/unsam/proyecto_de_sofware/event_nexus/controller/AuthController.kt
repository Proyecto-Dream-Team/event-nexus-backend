package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginResponse
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.NewAccountRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.security.JwtUtil
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8080", "http://localhost:5173", "http://localhost:3001"])
@RestController
@RequestMapping("/auth")
class AuthController(
    val authService: AuthService,
    val userService: UserService,
    val jwtUtil: JwtUtil
) {

    @PostMapping("/create")
    fun createAccount(@RequestBody newAccountRequest: NewAccountRequest, @RequestParam adminId:Long):ResponseEntity<String>{
        authService.checkExistintUsername(newAccountRequest.username)
        val admin: Admin = userService.getAdminById(adminId)
        authService.createAccount(newAccountRequest, admin)
        return ResponseEntity.ok().body("Account succesfully created!")
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest):LoginResponse{
        val user = authService.loadUserByUsername(loginRequest.username) as Credentials
        authService.validPassword(loginRequest.password, user)
        val employee: Employee = userService.getByCredentialsId(user.id!!)
        return LoginResponse(id=user.id!!, role = user.role.toString(), img=employee.image, token=jwtUtil.generate(user, employee.id!!))
    }

}
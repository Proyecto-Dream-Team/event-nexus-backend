package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginResponse
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.NewAccountRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/auth")
class AuthController(val authService: AuthService) {

    @PostMapping("/create")
    fun createAccount(@RequestBody newAccountRequest: NewAccountRequest, @RequestParam adminId:Long):ResponseEntity<String>{
        authService.checkExistintUsername(newAccountRequest.username)
        val admin: Admin = authService.getAdminById(adminId)
        authService.createAccount(newAccountRequest, admin)
        return ResponseEntity.ok().body("Account succesfully created!")
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest):ResponseEntity<LoginResponse>{
        lateinit var loginResponse:LoginResponse
        if(!authService.checkCredentialsBack(loginRequest.username, loginRequest.password)){
            loginResponse = authService.login(loginRequest)
        }else{
            throw BusinessException("Credenciales invalidas")
        }
        return ResponseEntity.ok().body(loginResponse)
    }

}
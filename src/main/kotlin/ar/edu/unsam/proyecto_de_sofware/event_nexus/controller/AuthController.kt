package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginResponse
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.NewAccountRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/auth")
class AuthController(val authService: AuthService) {

    @PostMapping("/create")
    fun createAccount(@RequestBody newAccountRequest: NewAccountRequest, @RequestParam role:String):ResponseEntity<String>{
        if(role!="admin") throw BusinessException("Solo los admin pueden crear")
        authService.createAccount(newAccountRequest)
        return ResponseEntity.ok().body("Account succesfully created!")
    }

    @PostMapping("/login")
    fun createAccount(@RequestBody loginRequest: LoginRequest):ResponseEntity<LoginResponse>{
        val loginResponse:LoginResponse = authService.login(loginRequest)
        return ResponseEntity.ok().body(loginResponse)
    }

    @GetMapping("/mock")
    fun mockGetAccounts(): List<Authentication>{
        return authService.mockGetAll()
    }
}
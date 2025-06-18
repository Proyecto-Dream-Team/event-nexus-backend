package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.NotFoundException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.EmailService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.utils.EmailSenderUtils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173", "http://localhost:3001"])
@RestController
@RequestMapping("/admin")
class AdminController(
    val authService: AuthService,
    val userService: UserService,
    @Autowired val emailService: EmailService
) {
    val emailUtil = EmailSenderUtils()

    @PostMapping("/create-user")
    fun createUser(@RequestBody userDto: UserCreateDTO, request: HttpServletRequest): ResponseEntity<String>{
        if(!userService.uniqueEmail(userDto.email)){
            throw BusinessException("Email ya existente")
        }
        val employee = userDto.toEmployee()
        userService.create(employee)
        emailService.sendEmail(
            to = employee.email,
            subject = emailUtil.subjectCreationUser(),
            content = emailUtil.contentCreationUser(employee.name)
        )
        return ResponseEntity.ok().body("Usuario creado, mail enviado")
    }

    @GetMapping("/edit-user/{employeeId}")
    fun getUserInfoToEdit(@PathVariable employeeId: Long, request: HttpServletRequest): UserCreateDTO{
        val employee = userService.getByID(employeeId)
        return employee.toUserCreateDTO()
    }

    @PutMapping("/edit-user")
    fun editUser(@RequestBody editEmployeeDTO: UserCreateDTO, request: HttpServletRequest): ResponseEntity<String>{
        val employee = userService.getByID(editEmployeeDTO.id!!)
        employee.editFromAdmin(editEmployeeDTO)
        userService.edit(employee)
        emailService.sendEmail(
            to = editEmployeeDTO.email,
            subject = emailUtil.subjectEditProfile(),
            content = emailUtil.contentEditProfile(editEmployeeDTO.name)
        )
        return ResponseEntity.ok().body("Usuario editado, mail enviado")
    }

    @GetMapping("/permissions-role")
    fun getPermissions(request: HttpServletRequest): CreteDataDTO{
        return CreteDataDTO(Role.entries, Permission.entries.map{ it.permissionName })
    }

    @PutMapping("/register")
    fun register(@RequestBody registerDTO: RegisterDTO, request: HttpServletRequest): ResponseEntity<String>{
        val credential = userService.getCredentialsByEmail(registerDTO.email)
        if(credential.validateRegister()){
            credential.setNewCredentials(registerDTO.username, authService.encode(registerDTO.password))
            authService.update(credential)
        }else{
            throw NotFoundException("Credenciales invalidadas")
        }

        return ResponseEntity.ok().body("Credenciales generadas")
    }

    @PutMapping("/recovery")
    fun setPass(@RequestBody registerDTO: RegisterDTO, request: HttpServletRequest): ResponseEntity<String>{
        val credential = userService.getCredentialsByEmail(registerDTO.email)
        if(!credential.validateRegister() && credential.validateUsername(registerDTO.username)){
            credential.setNewCredentials(registerDTO.username, authService.encode(registerDTO.password))
            authService.update(credential)
        }else{
            throw NotFoundException("Credenciales invalidadas")
        }
        return ResponseEntity.ok().body("Password actualizado")
    }

    @DeleteMapping("/delete/user/{employeeId}")
    fun delete(@PathVariable employeeId: Long, request: HttpServletRequest): ResponseEntity<String>{
        try {
            userService.deleteEmployee(employeeId)
        }catch (e: DataBaseNotModifiedException){
            return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(e.message)
        }
        return ResponseEntity.ok().body("Usuario eliminado")
    }


}
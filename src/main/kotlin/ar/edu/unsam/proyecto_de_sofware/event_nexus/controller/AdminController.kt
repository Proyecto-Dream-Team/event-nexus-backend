package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.EditEmployeeDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ProfileDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.UserCreateDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.EmailService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.utils.EmailSenderUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/admin")
class AdminController(
    val authService: AuthService,
    val userService: UserService<Any?>,
    @Autowired val emailService: EmailService
) {
    val emailUtil = EmailSenderUtils()

    @PostMapping("/permission/grant")
    fun grantPermissions(
        @RequestBody permissions: List<Permission>,
        @RequestParam adminId:Long,
        @RequestParam employeeId:Long
    ): ResponseEntity<String> {
        val admin: Admin = authService.getAdminById(adminId = adminId)
        authService.checkPermission(employee=admin, permission = Permission.ADMIN_PERMISSION)
        val employee: Employee = userService.getByID(employeeId)
        admin.addPermissions(employee, permissions.toSet())
        userService.saveEmployee(employee)
        return ResponseEntity.ok().body("Permisos concedidos!")
    }

    @PostMapping("/permission/revoke")
    fun revoquePermissions(
        @RequestBody permissions: List<Permission>,
        @RequestParam adminId:Long,
        @RequestParam employeeId:Long
    ): ResponseEntity<String> {
        val admin: Admin = authService.getAdminById(adminId = adminId)
        authService.checkPermission(employee=admin, permission = Permission.ADMIN_PERMISSION)
        val employee: Employee = userService.getByID(employeeId)
        admin.deletePermissions(employee, permissions.toSet())
        userService.saveEmployee(employee)
        return ResponseEntity.ok().body("Permisos revocados!")
    }

    @PostMapping("/create-user")
    fun createUser(@RequestBody userDto: UserCreateDTO): ResponseEntity<String>{
        val employee = userDto.toEmployee()
        userService.create(employee)
        emailService.sendEmail(
            to = employee.email,
            subject = emailUtil.subjectCreationUser(),
            content = emailUtil.contentCreationUser(employee.name)
        )
        return ResponseEntity.ok().body("Usuario creado, mail enviado")
    }

    @PutMapping("/edit-user")
    fun editUser(@RequestBody editEmployeeDTO: EditEmployeeDTO): ResponseEntity<String>{
        val employee = userService.getByID(editEmployeeDTO.id)
        employee.editFromAdmin(editEmployeeDTO)
        userService.edit(employee)
        emailService.sendEmail(
            to = editEmployeeDTO.email,
            subject = emailUtil.subjectEditProfile(),
            content = emailUtil.contentEditProfile(editEmployeeDTO.name)
        )
        return ResponseEntity.ok().body("Usuario editado, mail enviado")
    }

}
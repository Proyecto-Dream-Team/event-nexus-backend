package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.UserCreateDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/admin")
class AdminController(
    val authService: AuthService,
    val userService: UserService,
) {

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
        return ResponseEntity.ok().body("Usuario creado")
    }

}
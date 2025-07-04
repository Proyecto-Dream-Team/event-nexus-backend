package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.DirectiveDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.toDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.NewDirectiveObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.DirectiveService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.NotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173", "http://localhost:3001"])
@RestController
@RequestMapping("/directive")
class DirectiveController(
    val directiveService: DirectiveService,
    val userService: UserService,
    private val directiveObserver: NewDirectiveObserver,
    val notificationService: NotificationService
) {
    @GetMapping()
    fun directives(): List<DirectiveDTO> {
        return directiveService.getAll().map { it.toDTO() }
    }

    @PostMapping("/create")
    fun createDirective(@RequestBody newDirectiveDTO: DirectiveDTO): ResponseEntity<String> {
        val creatorEmployee = userService.getByID(newDirectiveDTO.creatorId)
        val allEmployeesIds = userService.repoUser.findAll().toMutableSet()
        val newDirective = this.directiveService.create(
            directive = newDirectiveDTO.toEntity(creatorEmployee = creatorEmployee)
        )
        val notification: Notification = this.notificationService.save(
            Notification().apply {
                creator = creatorEmployee
                type = newDirective::class.simpleName!!
                listeners = allEmployeesIds
                title = "Informacion directiva recibida.< ${newDirective.title} >"
            }
        )
        directiveObserver.notifyNewDirective(newDirective, notification)
        return ResponseEntity.ok().body("Directiva creada!")
    }
}
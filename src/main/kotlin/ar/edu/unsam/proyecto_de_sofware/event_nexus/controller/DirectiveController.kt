package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.DirectiveDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.fromEventDTOtoEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.showEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.CreatedEventObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.NewDirectiveObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.DirectiveService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.EventService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.NotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.SseNotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173", "http://localhost:3001"])
@RestController
@RequestMapping("/directive")
class DirectiveController(
    val directiveService: DirectiveService,
    val userService: UserService,
    private val notifyObserver: CreatedEventObserver,
    private val directiveObserver: NewDirectiveObserver,
    val serviceSSE: SseNotificationService,
    val notificationService: NotificationService
) {
    @GetMapping()
    fun directives(): List<Directive> {
        return directiveService.getAll()
    }

    @PostMapping("/create")
    fun createDirective(@RequestBody newDirectiveDTO: DirectiveDTO): ResponseEntity<String> {
        val creatorEmployee = userService.getByID(newDirectiveDTO.creatorId)
//        this.eventService.checkPermission(employee = creatorEmployee, permission = Permission.CREAR_EVENTO_SOCIAL)
//        val participantsEmployees = userService.findAllById(employeesIds = newEventDTO.participantsIds.toList())
//        this.eventService.existTitle(newEventDTO.name)
        val newDirective = this.directiveService.create(
            directive = newDirectiveDTO.toEntity(creatorEmployee = creatorEmployee)
        )
        val notification: Notification = this.notificationService.save(
            Notification().apply {
                creator = creatorEmployee
                type = newDirective::class.simpleName!!
                listeners = mutableSetOf()
                title = "Informacion directiva. ${newDirective.title}"
            }
        )
//        notifyObserver.notify(newEvent, notification)
        return ResponseEntity.ok().body("Directiva creada!")
    }
//
//
//    @DeleteMapping()
//    fun delete(@RequestParam employeeId: Long, @RequestParam eventId: Long): ResponseEntity<String> {
//        val employee = userService.getByID(employeeId)
//        eventService.checkPermission(employee = employee, permission = Permission.CREAR_EVENTO_DEPORTIVO)
//        val event = eventService.getById(eventId)
//        try {
//            eventService.delete(event, employee)
//        } catch (error: DataBaseNotModifiedException) {
//            return ResponseEntity
//                .status(HttpStatus.NOT_MODIFIED)
//                .body(error.message)
//        }
//        return ResponseEntity
//            .status(HttpStatus.OK)
//            .body("Evento eliminado")
//    }
}
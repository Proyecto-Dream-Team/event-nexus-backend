package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.EventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.fromEventDTOtoEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.showEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventType
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.CreatedEventObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.NewDirectiveObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.EventService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.NotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.SseNotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import org.springframework.http.HttpStatus
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
@RequestMapping("/event")
class EventController(
    val eventService: EventService,
    val userService: UserService,
    private val notifyObserver: CreatedEventObserver,
    private val directiveObserver: NewDirectiveObserver,
    val serviceSSE: SseNotificationService,
    val notificationService: NotificationService
) {
    @GetMapping()
    fun events(): List<ShowEventDTO> {
        return eventService.findAllByPublic().map { it.showEventDTO() }
    }

    @GetMapping("/{id}")
    fun employeeEvents(@PathVariable id: Long): Map<String, List<ShowEventDTO>> {
        val employee: Employee = userService.getByID(id)
        val createdEvents = eventService.employeeCreatedEvents(employee).map { it.showEventDTO() }
        val invitedEvents = eventService.employeeInvitedEvents(employee).map { it.showEventDTO() }
        return mapOf("createdEvents" to createdEvents, "invitedEvents" to invitedEvents)
    }


    @GetMapping("/create")
    fun listEventTypes(): List<EventType> {
        return EventType.entries.toList()
    }

    @PostMapping("/create")
    fun createEvent(@RequestBody newEventDTO: EventDTO): ResponseEntity<String> {
        val creatorEmployee = userService.getByID(newEventDTO.creatorId)
        this.eventService.checkPermission(employee = creatorEmployee, permission = Permission.CREAR_EVENTO_SOCIAL)
        val participantsEmployees = userService.findAllById(employeesIds = newEventDTO.participantsIds.toList())
        val newEvent = eventService.createEvent(
            event = fromEventDTOtoEvent(
                creatorEmployee = creatorEmployee,
                participantsEmployees = participantsEmployees,
                eventDTO = newEventDTO
            )
        )
        val notification: Notification = this.notificationService.save(
            Notification().apply {
                creator = creatorEmployee
                type = newEvent::class.simpleName!!
                listeners = participantsEmployees.toMutableSet()
                title = "Te invitaron al evento ${newEvent.title}"
            }
        )
        notifyObserver.notify(newEvent, notification)
        return ResponseEntity.ok().body("Evento creado!")
    }

    @PostMapping("/join-leave")

    fun joinLeave(@RequestParam employeeId: Long, @RequestParam eventId: Long): ResponseEntity<String> {
        val employee: Employee = userService.getByID(employeeId)
        lateinit var event: Event
        event = eventService.getById(eventId)
        val initialAmmount: Int = event.participants.size
        eventService.joinLeave(event, employee)
        try {
            eventService.updateEvent(event)
        } catch (e: DataBaseNotModifiedException) {
            return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(e.message)
        }
        val notification: Notification = this.notificationService.save(
            Notification().apply {
                creator = employee
                type = event::class.simpleName!!
                listeners = mutableSetOf(event.creator)
                title =
                    if (event.participants.size > initialAmmount) "${employee.fullName()} se unio al evento ${event.title}" else "${employee.fullName()} abandono el evento ${event.title}"
            }
        )
        notifyObserver.notifyLeaveOrJoined(
            event = event,
            notification = notification,
            joined = event.participants.size > initialAmmount
        )
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Actualizacion exitosa!")
    }

    @PutMapping()
    fun modify(@RequestBody eventDTO: ShowEventDTO): ResponseEntity<String> {
        val event = eventService.getById(eventDTO.id!!)
        try {
            eventService.modify(event, eventDTO)
        } catch (e: DataBaseNotModifiedException) {
            return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(e.message)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Actualizacion exitosa!")
    }

    @DeleteMapping()
    fun delete(@RequestParam employeeId: Long, @RequestParam eventId: Long): ResponseEntity<String> {
        val employee = userService.getByID(employeeId)
        eventService.checkPermission(employee = employee, permission = Permission.CREAR_EVENTO_DEPORTIVO)
        val event = eventService.getById(eventId)
        try {
            eventService.delete(event, employee)
        } catch (error: DataBaseNotModifiedException) {
            return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(error.message)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Evento eliminado")
    }
}
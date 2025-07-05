package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.NewEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ResponseEntityDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.fromEventDTOtoEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.showEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.toEventParticipantDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventType
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.JoinLeaveObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.NewDirectiveObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer.NewEventObserver
import ar.edu.unsam.proyecto_de_sofware.event_nexus.security.JwtUtil
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.EventService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.NotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.SseNotificationService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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

@CrossOrigin(origins = ["http://localhost:8080", "http://localhost:5173", "http://localhost:3001"])
@RestController
@RequestMapping("/event")
class EventController(
    val eventService: EventService,
    val userService: UserService,
    private val joinLeaveObserver: JoinLeaveObserver,
    private val newEventObserver: NewEventObserver,
    val notificationService: NotificationService,
    val jwtUtil: JwtUtil
) {

    @GetMapping("/all")
    fun eventsAll(
        request: HttpServletRequest,
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "5") size: Int
    ): List<ShowEventDTO> {
        val idToken = jwtUtil.getId(request)
        val page: PageRequest = PageRequest.of(page-1, size, Sort.by("id"))
        return eventService.findALl(page).map { it.showEventDTO() }.toList()
    }

    @GetMapping("/available")
    fun events(request: HttpServletRequest): List<ShowEventDTO> {
        val idToken = jwtUtil.getId(request)
        return eventService.findAllByPublicAndNotFromEmployee(idToken).map { it.showEventDTO() }
    }

    @GetMapping("/title")
    fun eventsByTitle(@RequestParam(required = false, ) eventTitle: String?, request: HttpServletRequest): List<ShowEventDTO> {
        val idToken = jwtUtil.getId(request)
        return eventService.findByTitle(eventTitle,idToken).map { it.showEventDTO() }
    }

    @GetMapping("/type/{eventType}")
    fun eventsByEventType(@PathVariable eventType: EventType, request: HttpServletRequest): List<ShowEventDTO> {
        val idToken = jwtUtil.getId(request)
        return eventService.findByEventType(eventType, idToken).map { it.showEventDTO() }
    }

    @GetMapping("/created")
    fun employeeCreatedEvents(
        request: HttpServletRequest
    ): List<ShowEventDTO> {
        val idToken = jwtUtil.getId(request)
        val employee: Employee = userService.getByID(idToken)
        val createdEvents:List<ShowEventDTO> = eventService.employeeCreatedEvents(employee).map { it.showEventDTO() }
        return createdEvents
    }

    @GetMapping("/invited")
    fun employeeInvitedEvents(request: HttpServletRequest): List<ShowEventDTO> {
        val idToken = jwtUtil.getId(request)
        val employee: Employee = userService.getByID(idToken)
        val invitedEvents:List<ShowEventDTO> = eventService.employeeInvitedEvents(employee).map { it.showEventDTO() }
        return invitedEvents
    }

    @GetMapping("/create")
    fun listEventTypes(request: HttpServletRequest): List<EventType> {
        return EventType.entries.toList()
    }

    @GetMapping("/type/all")
    fun getEventTypes(request: HttpServletRequest): List<EventType> {
        return EventType.entries.toList()
    }

    @PostMapping("/create")
    fun createEvent(@RequestBody newEventDTO: NewEventDTO, request: HttpServletRequest): ResponseEntity<String> {
        val idToken = jwtUtil.getId(request)
        val creatorEmployee = userService.getByID(idToken)
//        this.eventService.checkPermission(employee = creatorEmployee, permission = Permission.CREAR_EVENTO_SOCIAL)
        val participantsEmployees = userService.findAllById(employeesIds = newEventDTO.participantsIds.toList())
        this.eventService.existTitle(newEventDTO.name)
        val newEvent = eventService.createEvent(
            event = fromEventDTOtoEvent(
                creatorEmployee = creatorEmployee,
                participantsEmployees = participantsEmployees,
                newEventDTO = newEventDTO
            )
        )
        val notification: Notification = this.notificationService.save(
            Notification().apply {
                creator = creatorEmployee
                type = newEvent::class.simpleName!!
                listeners = participantsEmployees.toMutableSet()
                title = "Te invitaron al Evento ${newEvent.title}"
            }
        )
        newEventObserver.notifyNewEvent(newEvent, notification)
        return ResponseEntity.ok().body("Evento creado!")
    }

    @PostMapping("/join-leave")
    fun joinLeave(@RequestParam eventId: Long, request: HttpServletRequest): ResponseEntity<ResponseEntityDTO> {
        val idToken = jwtUtil.getId(request)
        val employee: Employee = userService.getByID(idToken)
        lateinit var event: Event
        event = eventService.getById(eventId)
        val initialAmmount: Int = event.participants.size
        eventService.joinLeave(event, employee)
        try {
            event = eventService.updateEvent(event)
        } catch (e: DataBaseNotModifiedException) {
            return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(ResponseEntityDTO(
                    responseMessage = "Error al actualizar!",
                    responseBody = listOf()
                ))
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
        joinLeaveObserver.notifyLeaveOrJoined(
            event = event,
            notification = notification,
            joined = event.participants.size > initialAmmount
        )
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseEntityDTO(
                responseMessage = if (event.participants.size > initialAmmount) "Te uniste al evento exitosamente!" else "Abandonaste el exitosamente!",
                responseBody = event.participants.map { it.toEventParticipantDTO() }
            ))
    }

    @PutMapping()
    fun modify(@RequestBody eventDTO: ShowEventDTO,request: HttpServletRequest): ResponseEntity<String> {

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
    fun delete(@RequestParam eventId: Long, request: HttpServletRequest): ResponseEntity<String> {
        val idToken = jwtUtil.getId(request)
        val employee = userService.getByID(idToken)
//        eventService.checkPermission(employee = employee, permission = Permission.CREAR_EVENTO_DEPORTIVO)
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
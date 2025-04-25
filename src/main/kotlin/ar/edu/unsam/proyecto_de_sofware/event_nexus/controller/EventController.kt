package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.EventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CreateEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.EventService
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/event")
class EventController(
    val eventService: EventService,
    val userService: UserService
) {
    @GetMapping()
    fun events(): List<ShowEventDTO> {
        return eventService.eventos()
    }

    @GetMapping("/{id}/created")
    fun createdEvents(@PathVariable id: Long): List<Event> {
        return eventService.employeeCreatedEvents(id)
    }

    @GetMapping("{id}/invited")
    fun invitedEvents(@PathVariable id: Long): List<Event> {
        return eventService.employeeInvitedEvents(id)
    }

    @PostMapping("/create")
    fun createEvent(@RequestBody newEventDTO: EventDTO): ResponseEntity<String> {
        val creatorEmployee: Employee = userService.getByID(newEventDTO.creatorId)
        val participantsEmployees: List<Employee> = userService.findAllById(employeesIds = newEventDTO.participantsIds)

        val newEvent: Event = Event().apply {
            creator = creatorEmployee
            participants = participantsEmployees.toMutableList()
            name = newEventDTO.name
            date = newEventDTO.date
            description = newEventDTO.description
            dateFinished = newEventDTO.dateFinished
        }
//        creatorEmployee.canDoModuleAction(command=CreateEvent())
        eventService.createEvent(newEvent, creatorEmployee)
        return ResponseEntity.ok().body("Evento creado!")
    }

    @PostMapping("/join")
    fun joinEvent(@RequestParam employeeId: Long, @RequestParam eventId: Long): ResponseEntity<String> {
        val employee: Employee = userService.getByID(employeeId)
        val event: Event = eventService.getById(eventId)
        event.addParticipant(employee)
        eventService.updateEvent(event)
        return ResponseEntity.ok().body("Te uniste al evento!")
    }

    @PostMapping("/leave")
    fun leaveEvent(@RequestParam employeeId: Long, @RequestParam eventId: Long): ResponseEntity<String> {
        val employee: Employee = userService.getByID(employeeId)
        val event: Event = eventService.getById(eventId)
        event.removeParticipant(employee)
        eventService.updateEvent(event)
        return ResponseEntity.ok().body("Abandonaste el evento!")
    }

}
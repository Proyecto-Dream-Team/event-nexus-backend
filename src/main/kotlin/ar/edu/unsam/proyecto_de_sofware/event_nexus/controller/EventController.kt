package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.EventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.showEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.EventService
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
import java.time.LocalDateTime

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping("/event")
class EventController(
    val eventService: EventService,
    val userService: UserService
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


    @PostMapping("/create")
    fun createEvent(@RequestBody newEventDTO: EventDTO): ResponseEntity<String> {
        val creatorEmployee = userService.getByID(newEventDTO.creatorId)
        val participantsEmployees = userService.findAllById(employeesIds = newEventDTO.participantsIds.toList())
        val newEvent = Event().apply {
            creator = creatorEmployee
            participants = participantsEmployees.toMutableSet()
            title = newEventDTO.name
            date = LocalDateTime.now()
            description = newEventDTO.description
            dateFinished = newEventDTO.date
        }
//      creatorEmployee.canDoModuleAction(command=CreateEvent())
        eventService.createEvent(newEvent, creatorEmployee)
        return ResponseEntity.ok().body("Evento creado!")
    }

    @PostMapping("/join-leave")
    fun joinLeave(@RequestParam employeeId: Long, @RequestParam eventId: Long): ResponseEntity<String> {
        val employee: Employee = userService.getByID(employeeId)
        val event: Event = eventService.getById(eventId)
        eventService.joinLeave(event, employee)
        try{
            eventService.updateEvent(event)
        }catch (e: DataBaseNotModifiedException){
            return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(e.message)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Actualizacion exitosa!")
    }

    @PutMapping()
    fun modify(@RequestBody eventDTO: EventDTO): ResponseEntity<String>{
        val event = eventService.getById(eventDTO.id)
        try{
            eventService.modify(event, eventDTO)
        }catch (e: DataBaseNotModifiedException){
            return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(e.message)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Actualizacion exitosa!")
    }

    @DeleteMapping()
    fun delete(@RequestParam employeeId: Long, @RequestParam eventId: Long): ResponseEntity<String>{
        val event = eventService.getById(eventId)
        val employee = userService.getByID(employeeId)
        try{
            eventService.delete(event, employee)
        }catch (error: DataBaseNotModifiedException){
            return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body(error.message)
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Evento eliminado")
    }
}
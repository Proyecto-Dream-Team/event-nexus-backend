package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.EventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.showEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.EventRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventService(
    val eventRepository: EventRepository,
) {
    fun getById(eventId: Long): Event {
        return eventRepository.findById(eventId).orElseThrow{ BusinessException("No se encontro Evento")}
    }

    fun findAllByPublic(): List<Event> {
        return eventRepository.findAllByPublic()
    }

    fun employeeCreatedEvents(employee: Employee): List<Event> {
        return eventRepository.findByCreator(employee)
    }

    fun employeeInvitedEvents(employee: Employee): List<Event> {
        return eventRepository.findByParticipants(employee)
    }

    @Transactional
    fun createEvent(event: Event, creator: Employee) {
        try {
            eventRepository.save(event)
        } catch (e: DataAccessException) {
            throw RuntimeException("No se pudo actualizar eventos")
        }
    }

    @Transactional
    fun updateEvent(event: Event) {
        try {
            eventRepository.save(event)
        } catch (e: DataAccessException) {
            throw DataBaseNotModifiedException("No se pudo actualizar eventos")
        }
    }

    @Transactional
    fun modify(event: Event, eventDTO: EventDTO) {
        event.fromDTO(eventDTO)
        return updateEvent(event)
    }

    fun joinLeave(event: Event, employee: Employee) {
        if(event.employeeParticipates(employee)){
            event.removeParticipant(employee)
        }else{
            event.addParticipant(employee)
        }
    }
}
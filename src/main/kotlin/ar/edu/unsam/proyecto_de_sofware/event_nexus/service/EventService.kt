package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.showEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.EventRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventService(
    val eventRepository: EventRepository,
    val userRepository: UserRepository,
) {
    fun getById(eventId: Long): Event {
        try {
            val event = eventRepository.findById(eventId).orElseThrow()
            return event
        } catch (e: Exception) {
            throw e
        }
    }

    fun events(): List<Event> {
        return eventRepository.findAll().toList()
    }

    fun employeeCreatedEvents(employeeId: Long): List<Event> {
        val employee: Employee = userRepository.findById(employeeId).get()
        val employeeEvents: List<Event> = eventRepository.findByCreator(employee)
        return employeeEvents
    }

    fun employeeInvitedEvents(employeeId: Long): List<Event> {
        val employee: Employee = userRepository.findById(employeeId).get()
        val employeeEvents: List<Event> = eventRepository.findByParticipants(employee)
        return employeeEvents
    }

    @Transactional
    fun createEvent(event: Event, creator: Employee) {
        try {
            eventRepository.save(event)
        } catch (e: Exception) {
            throw e
        }
    }

    @Transactional
    fun updateEvent(event: Event) {
        try {
            eventRepository.save(event)
        } catch (e: Exception) {
            throw e
        }
    }
}
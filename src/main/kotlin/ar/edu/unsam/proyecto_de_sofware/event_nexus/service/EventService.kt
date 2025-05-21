package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.EventRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.NotificationRepository
import jakarta.transaction.Transactional
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service


@Service
class EventService(
    val eventRepository: EventRepository,
    val notificationRepository: NotificationRepository
) {
    private val eventModule: EventModule = EventModule()

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
    fun createEvent(event: Event):Event {
        try {
            return eventRepository.save(event)
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
    fun modify(event: Event, eventDTO: ShowEventDTO) {
        event.fromDTO(eventDTO)
        return updateEvent(event)
    }

    fun joinLeave(event: Event, employee: Employee)  {
        if(event.employeeParticipates(employee)){
            event.removeParticipant(employee)
        }else{
            event.addParticipant(employee)
        }
    }

    fun delete(event: Event, employee: Employee) {
        if(event.isCreator(employee.id!!)){
            try {
                eventRepository.delete(event)
            }catch (error: DataAccessException){
                throw DataBaseNotModifiedException("No se puede eliminar")
            }
        }else{
            throw BusinessException("No eres el creador del evento")
        }
    }

    fun checkPermission(employee: Employee, permission: Permission){
        this.eventModule.checkPermission(employeePermmission = employee.permissions, permissionToCheck = permission)
    }
}
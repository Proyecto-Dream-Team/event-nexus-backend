package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.EventRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.ModuleRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class MockService(
    val authRepository: AuthRepository,
    val userRepository: UserRepository,
    val moduleRepository: ModuleRepository,
    val eventRepository: EventRepository,
    ) {

    fun eventos():List<Event>{
        return eventRepository.findAll().toList()
    }

    fun modulosAll():List<AppModule>{
        return moduleRepository.findAll().toList()
    }

    fun employeeModulosById(employeeId:Long):List<AppModule>{
        val employee:Employee = userRepository.findById(employeeId).get()
        return employee.modules.toList()
    }

    fun employeeCreatedEvents(employeeId:Long):List<Event>{
        val employee:Employee = userRepository.findById(employeeId).get()
        val employeeEvents: List<Event> = eventRepository.findByCreator(employee)
        return employeeEvents
    }

    fun employeeInvitedEvents(employeeId:Long):List<Event>{
        val employee:Employee = userRepository.findById(employeeId).get()
        val employeeEvents: List<Event> = eventRepository.findByParticipants(employee)
        return employeeEvents
    }
}
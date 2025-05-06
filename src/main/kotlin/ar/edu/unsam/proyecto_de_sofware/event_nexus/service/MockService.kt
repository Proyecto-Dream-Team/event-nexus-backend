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
    val eventRepository: EventRepository,
) {

    fun getUser(id: Long): Employee {
        return userRepository.findById(id).get()
    }


    fun getAdmin(id: Long): Admin? {
        val user = userRepository.findById(id).get()
        if (user !is Admin) {
            throw BusinessException("")
        }
        return user
    }
}
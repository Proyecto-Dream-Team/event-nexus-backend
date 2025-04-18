package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService(
     val eventRepo: EventRepository
) {
//    fun getAllEvent(user: Employee): Set<Event> {
//        return eventRepo
//    }
}
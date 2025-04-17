package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<Event, Long> {
}
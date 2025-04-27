package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.PathVariable

interface EventRepository: CrudRepository<Event, Long> {

    fun findByCreator(creator: Employee): List<Event>

    fun findByParticipants(participant: Employee): List<Event>

    fun findAllByPublic(public: Boolean = true): List<Event>
}
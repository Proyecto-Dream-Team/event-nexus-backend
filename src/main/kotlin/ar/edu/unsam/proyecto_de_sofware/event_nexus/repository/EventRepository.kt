package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventType
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface EventRepository: CrudRepository<Event, Long> {

    fun findByCreator(creator: Employee): List<Event>

    fun findByParticipants(participant: Employee): List<Event>

    fun findAllByPublicAndCreator_IdNot(public:Boolean = true, employeeId: Long): List<Event>

    fun existsEventByTitle(title: String): Boolean

    fun findEventsByTypeIs(eventType: EventType): List<Event>
    @Query(value="""
        SELECT
            e FROM Event e
        WHERE 
            LOWER(e.title) LIKE CONCAT('%', :eventTitle, '%')
    """)
    fun findEventsByTitleContaining(@Param("eventTitle") eventTitle: String): List<Event>
}
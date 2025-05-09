package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.PathVariable

interface EventRepository: CrudRepository<Event, Long> {

    fun findByCreator(creator: Employee): List<Event>

    fun findByParticipants(participant: Employee): List<Event>

    fun findAllByPublic(public: Boolean = true): List<Event>

    @Query("""
    SELECT new ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO(
        e.id,
        creator.name,
        creator.image,
        creator.id,
        e.dateFinished,
        e.title,
        e.description,
        SIZE(e.participants),
        (SELECT p.id FROM e.participants p),
        e.type
    )
    FROM Event e
    INNER JOIN e.creator creator
    LEFT JOIN e.participants participant
    WHERE e.date > CURRENT_TIMESTAMP
    GROUP BY e.id, creator.name, creator.image, creator.id,
             e.dateFinished, e.title, e.description, e.type
    ORDER BY e.id
    """)
    fun findByAllPendings(): List<ShowEventDTO>
}
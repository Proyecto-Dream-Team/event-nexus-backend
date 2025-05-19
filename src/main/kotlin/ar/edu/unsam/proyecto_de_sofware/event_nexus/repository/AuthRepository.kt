package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.PathVariable

interface AuthRepository: CrudRepository<Authentication, Long> {

    fun findByUsername(username: String): Authentication?

}
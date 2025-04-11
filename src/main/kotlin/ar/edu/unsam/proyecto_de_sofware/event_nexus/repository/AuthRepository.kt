package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import org.springframework.data.repository.CrudRepository

interface AuthRepository: CrudRepository<Authentication, Long> {

    fun findByUsername(username: String): Authentication
}
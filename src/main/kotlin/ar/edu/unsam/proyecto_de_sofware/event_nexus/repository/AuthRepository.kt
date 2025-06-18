package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import org.springframework.data.repository.CrudRepository

interface AuthRepository: CrudRepository<Credentials, Long> {

    fun findByUsername(username: String): Credentials?

}
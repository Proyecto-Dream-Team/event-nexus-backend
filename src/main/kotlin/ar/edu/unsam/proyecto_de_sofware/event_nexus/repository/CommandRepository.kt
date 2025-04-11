package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import org.springframework.data.repository.CrudRepository

interface CommandRepository: CrudRepository<ModuleCommand, Long> {

//    fun findByUsername(username: String): Authentication
}
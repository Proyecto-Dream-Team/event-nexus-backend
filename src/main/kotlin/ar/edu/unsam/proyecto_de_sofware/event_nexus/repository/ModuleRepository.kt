package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import org.springframework.data.repository.CrudRepository

interface ModuleRepository: CrudRepository<AppModule, Long>  {

    fun findByName(name: String): AppModule
}
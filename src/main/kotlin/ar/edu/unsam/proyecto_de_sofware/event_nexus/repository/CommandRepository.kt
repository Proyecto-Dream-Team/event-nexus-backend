package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface CommandRepository: CrudRepository<ModuleCommand, Long> {
    
    @Query(nativeQuery = true,
        value = """
            SELECT c.* FROM module_command c
            INNER JOIN employee_module_command emc ON c.id = emc.module_command_id
            WHERE emc.employee_id = :usuarioId
            """)
    fun findEmployeeCommandsByID(usuarioId:Long): List<ModuleCommand>

}
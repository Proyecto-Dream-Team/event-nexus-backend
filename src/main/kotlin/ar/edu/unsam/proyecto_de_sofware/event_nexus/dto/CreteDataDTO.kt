package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission

data class CreteDataDTO(
    val roles: List<Role>,
    val permissions: List<String>
)

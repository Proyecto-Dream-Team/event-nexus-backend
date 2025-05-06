package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.admin.AdminModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports.RepportModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule


class ModuleRepository {
    val modules:List<AppModule> = listOf(EventModule(), DirectiveModule(), RepportModule(),AdminModule())

    fun findAll():List<AppModule> = this.modules
}

package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive

fun directive01(creatorEmployee: Employee): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Directiva de Adrian"
        description = "Maniana hacemos daily"
    }
}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectivePriority

fun directive01(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Directiva de Adrian"
        description = "Maniana hacemos daily"
        priority = directivePriority

    }
}

fun directive02(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Directiva de Adrian"
        description = "Viernes cierre de sprint"
        priority = directivePriority

    }
}

fun directive03(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Directiva de Adrian"
        description = "Reunion para determinar detalles "
        priority = directivePriority

    }
}
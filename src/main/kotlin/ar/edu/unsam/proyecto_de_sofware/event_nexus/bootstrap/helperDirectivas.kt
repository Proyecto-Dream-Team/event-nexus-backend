package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectivePriority

fun directive01(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Ultima Daily del mes"
        description = "Maniana hacemos la ultima daily del mes. Es importantisimo poder cerrar todas las tareas pendientes antes del cierre del sprint, ya que esta la posibilidad de que en el proximo realicemos un nuevo modulo."
        priority = directivePriority

    }
}

fun directive02(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Cierre de Sprint"
        description = "El Viernes será el cierre de sprint, por lo tanto es fundamental que todos los entregables estén listos, revisados y cargados antes del mediodía para garantizar una demo fluida y sin contratiempos."
        priority = directivePriority

    }
}

fun directive03(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Reunion de Proyecto"
        description = "El Viernes será el cierre de sprint, por lo que tendremos una reunión para repasar lo trabajado, compartir observaciones y definir detalles pendientes de cara al próximo ciclo."
        priority = directivePriority

    }
}
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

fun directive04(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Parcial de Paradigmas"
        description = "Recuerden estudiar paradigmas logico y funcional"
        priority = directivePriority

    }
}

fun directive05(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Coloquio de PHM"
        description = "Acuerdense que el coloquio es tranqui, los dijo NICO!!!!"
        priority = directivePriority

    }
}

fun directive06(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Repartir presentacion + demo"
        description = "El miercoles es la presentacion de la app. Recuerden practicar la demo"
        priority = directivePriority

    }
}

fun directive07(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Coche con la luz prendida"
        description = "Vehiculo de matricula AAA-BBB-CCC con las luces prendidas en el parking 12"
        priority = directivePriority

    }
}
fun directive08(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Reparacion de oficina"
        description = "Durante las fechas de 17/06/2025 al 27/06/2025 la sala comun esta clausurada debido a reparaciones."
        priority = directivePriority

    }
}

fun directive09(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Billetera perdida"
        description = "Se perdio una billetera, acercarse a bedelia."
        priority = directivePriority

    }
}

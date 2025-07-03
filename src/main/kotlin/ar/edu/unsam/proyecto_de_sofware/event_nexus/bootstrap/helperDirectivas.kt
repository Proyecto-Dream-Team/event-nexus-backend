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

fun directive10(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Revisión de Seguridad de Datos"
        description = "Es crucial realizar una auditoría completa de los sistemas para identificar vulnerabilidades y asegurar la integridad de los datos de la empresa. Prioridad máxima para evitar brechas."
        priority = directivePriority
    }
}

fun directive11(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Capacitación en Nuevas Tecnologías"
        description = "Se organizará un ciclo de talleres sobre las últimas tendencias en desarrollo de software, incluyendo Inteligencia Artificial y Machine Learning. Todos los desarrolladores deberán asistir."
        priority = directivePriority
    }
}

fun directive12(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Mantenimiento Preventivo de Servidores"
        description = "Programar y ejecutar el mantenimiento preventivo de todos los servidores de producción durante el fin de semana para minimizar el impacto en las operaciones diarias. Se enviará un cronograma detallado."
        priority = directivePriority
    }
}

fun directive13(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Evaluación de Clima Laboral Anual"
        description = "Realizar la encuesta de clima laboral para recolectar feedback de los empleados y detectar áreas de mejora en el ambiente de trabajo. La participación es anónima y muy importante."
        priority = directivePriority
    }
}

fun directive14(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Actualización de Políticas de Contratación"
        description = "Revisar y actualizar las políticas de contratación para adaptarlas a las nuevas leyes laborales y asegurar la inclusión y diversidad en el proceso de selección de personal."
        priority = directivePriority
    }
}

fun directive15(creatorEmployee: Employee, directivePriority: DirectivePriority): Directive {
    return Directive().apply {
        creator = creatorEmployee
        title = "Desarrollo de un Nuevo Sistema de Reportes"
        description = "Iniciar el proyecto para la creación de un sistema de reportes unificado y más intuitivo para el departamento de finanzas. Se formará un equipo multidisciplinario."
        priority = directivePriority
    }
}

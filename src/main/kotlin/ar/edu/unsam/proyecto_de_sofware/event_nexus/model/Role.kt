package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CancelEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CreateEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.ScheduleEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand

enum class Role(val jobName: String){
    EMPLOYEE_WATCHER(
        jobName = "Employee noob",
//        permissions = setOf()
    ),
    EMPLOYEE_SIMPLE(
        jobName = "Employee simple",
//        permissions = setOf(CreateEvent())
    ),
    EMPLOYEE_FULL(
        jobName = "Employee full",
//        permissions = setOf(CreateEvent(), ScheduleEvent())
    ),
    ADMIN(
        jobName = "Admin",
//        permissions = setOf(CreateEvent(), CancelEvent(), ScheduleEvent())
    )
}
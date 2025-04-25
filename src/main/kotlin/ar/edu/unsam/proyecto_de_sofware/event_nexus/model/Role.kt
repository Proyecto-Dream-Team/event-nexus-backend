package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CancelEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CreateEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.ScheduleEvent


enum class Role(val jobName: String){
    EMPLOYEE_WATCHER(
        jobName = "Employee noob"
    ),
    EMPLOYEE_SIMPLE(
        jobName = "Employee simple"
    ),
    EMPLOYEE_FULL(
        jobName = "Employee full"
    ),
    ADMIN(
        jobName = "Admin"
    )
}
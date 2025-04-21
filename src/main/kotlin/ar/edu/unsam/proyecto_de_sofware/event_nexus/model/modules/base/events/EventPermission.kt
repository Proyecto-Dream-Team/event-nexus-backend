package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModulePermission


abstract class EventPermission: ModulePermission() {
    override val module: EventModule = EventModule()
}

class CreateEvent: EventPermission() {
    override fun getClassName(): String = "Create Event"
}

class CancelEvent: EventPermission() {
    override fun getClassName(): String = "Cancel Event"
}

class ScheduleEvent: EventPermission() {
    override fun getClassName(): String = "Schedule Event"
}


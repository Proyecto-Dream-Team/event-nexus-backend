package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand

abstract class EventModuleCommand: ModuleCommand() {
    val module = EventModule()
    abstract override fun doExecute()
}

class CreateEvent: EventModuleCommand() {
    override fun doExecute() {
        module.notifyEvent()
    }

    override fun getClassName(): String = "Create Event"

}

class CancelEvent: EventModuleCommand() {
    override fun doExecute() {
        module.cancelEvent()
    }

    override fun getClassName(): String = "Cancel Event"

}

class ScheduleEvent: EventModuleCommand() {
    override fun doExecute() {
        module.scheduleEvent()
    }

    override fun getClassName(): String = "Schedule Event"

}

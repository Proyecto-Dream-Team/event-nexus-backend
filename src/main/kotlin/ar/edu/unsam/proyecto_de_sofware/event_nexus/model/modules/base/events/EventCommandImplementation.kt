package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import jakarta.persistence.Entity

@Entity
class CreateEvent: EventCommand() {
    override fun doExecute() {
        module.notifyEvent()
    }

    override fun getClassName(): String = "Create Event"

}

@Entity
class CancelEvent: EventCommand() {
    override fun doExecute() {
        module.cancelEvent()
    }

    override fun getClassName(): String = "Cancel Event"

}

@Entity
class ScheduleEvent: EventCommand() {
    override fun doExecute() {
        module.scheduleEvent()
    }

    override fun getClassName(): String = "Schedule Event"

}

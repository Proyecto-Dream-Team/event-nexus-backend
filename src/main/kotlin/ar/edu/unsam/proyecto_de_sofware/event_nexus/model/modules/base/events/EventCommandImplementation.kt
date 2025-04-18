package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class CreateEvent() : EventCommand() {

    override fun doExecute() {
        (module as EventModule).notifyEvent(this.event!!)
    }

    override fun getClassName(): String = "Create Event"

}

@Entity
class CancelEvent() : EventCommand() {
    override fun doExecute() {
        (module as EventModule).cancelEvent(this.event!!)
    }

    override fun getClassName(): String = "Cancel Event"

}

@Entity
class ScheduleEvent() : EventCommand() {
    override fun doExecute() {
        (module as EventModule).scheduleEvent(this.event!!)
    }

    override fun getClassName(): String = "Schedule Event"

}

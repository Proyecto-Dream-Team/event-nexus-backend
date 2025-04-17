package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

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
class ScheduleEvent(): EventCommand() {
    @ManyToOne
    lateinit var event: Event
    override fun doExecute() {
        module.scheduleEvent(event)
    }

    override fun getClassName(): String = "Schedule Event"

}

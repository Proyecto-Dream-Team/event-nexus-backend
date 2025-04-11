package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
abstract class EventModuleCommand(
    @Transient
    @JsonIgnore
    val module: EventModule = EventModule()
): ModuleCommand() {

    @Column
    val moduleName = this.module::class.simpleName

    abstract override fun doExecute()
}

@Entity
class CreateEvent: EventModuleCommand() {
    override fun doExecute() {
        module.notifyEvent()
    }

    override fun getClassName(): String = "Create Event"

}

@Entity
class CancelEvent: EventModuleCommand() {
    override fun doExecute() {
        module.cancelEvent()
    }

    override fun getClassName(): String = "Cancel Event"

}

@Entity
class ScheduleEvent: EventModuleCommand() {
    override fun doExecute() {
        module.scheduleEvent()
    }

    override fun getClassName(): String = "Schedule Event"

}

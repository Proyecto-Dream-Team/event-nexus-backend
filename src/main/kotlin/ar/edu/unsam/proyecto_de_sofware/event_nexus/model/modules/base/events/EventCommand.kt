package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
abstract class EventCommand: ModuleCommand() {

    @Transient
    @JsonIgnore
    override val module: EventModule = EventModule()


    @Column
    val moduleName = this.module::class.simpleName

    abstract override fun doExecute()
}
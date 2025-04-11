package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
abstract class DirectiveCommand(
    @Transient
    @JsonIgnore
    val module: DirectiveModule = DirectiveModule()
): ModuleCommand() {

    @Column
    val moduleName = this.module::class.simpleName

    abstract override fun doExecute()
}
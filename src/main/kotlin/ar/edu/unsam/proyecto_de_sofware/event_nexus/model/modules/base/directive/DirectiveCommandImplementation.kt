package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventCommand
import jakarta.persistence.Entity

@Entity
class SendDirective: DirectiveCommand() {
    override fun doExecute() {
        module.mock()
    }

    override fun getClassName(): String = "Send Directive"

}
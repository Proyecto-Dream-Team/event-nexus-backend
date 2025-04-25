package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModulePermission


abstract class DirectivePermission(): ModulePermission() {
    override val module: DirectiveModule = DirectiveModule()

}

class SendDirective: DirectivePermission() {

    override fun getClassName(): String = "Send Directive"

}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification

interface NewDirectiveListener {

    fun notifyDirective(directive: Directive, notification: Notification)
}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener.NewDirectiveListener
import org.springframework.stereotype.Component

@Component
class NewDirectiveObserver: GenericObserver<Directive, NewDirectiveListener>()
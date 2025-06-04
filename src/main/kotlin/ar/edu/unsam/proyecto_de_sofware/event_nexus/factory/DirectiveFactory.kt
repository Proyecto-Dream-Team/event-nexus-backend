package ar.edu.unsam.proyecto_de_sofware.event_nexus.factory

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectivePriority

class DirectiveFactory {

    fun newDirective(creatorEmployee: Employee,titleDirective : String, descriptionInput:String, priorityInput: DirectivePriority):Directive{
        return Directive().apply{
            creator = creatorEmployee
            title = titleDirective
            description = descriptionInput
            priority = priorityInput
        }
    }
}
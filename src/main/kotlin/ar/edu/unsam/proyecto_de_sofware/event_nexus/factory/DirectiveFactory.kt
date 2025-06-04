package ar.edu.unsam.proyecto_de_sofware.event_nexus.factory

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectivePriority

class DirectiveFactory {

    fun newDirective(creatorEmployee: Employee, descriptionInput:String, priorityInput: DirectivePriority):Directive{
        return Directive().apply{
            creator = creatorEmployee
            title = "Directiva de ${creatorEmployee.name}"
            description = descriptionInput
            priority = priorityInput
        }
    }
}
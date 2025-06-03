package ar.edu.unsam.proyecto_de_sofware.event_nexus.factory

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive

class DirectiveFactory {

    fun newDirective(creatorEmployee: Employee, titleInput:String, descriptionInput:String):Directive{
        return Directive().apply{
            creator = creatorEmployee
            title = titleInput
            description = descriptionInput
        }
    }
}
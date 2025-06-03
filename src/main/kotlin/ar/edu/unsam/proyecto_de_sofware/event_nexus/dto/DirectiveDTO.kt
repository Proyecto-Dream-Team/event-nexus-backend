package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.factory.DirectiveFactory
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import java.time.LocalDateTime

data class DirectiveDTO(
    val creatorId: Long,
    val title:String,
    val date: LocalDateTime,
    val description:String
){
    fun toEntity(creatorEmployee: Employee): Directive{
        return DirectiveFactory().newDirective(creatorEmployee, title, description)
    }
}


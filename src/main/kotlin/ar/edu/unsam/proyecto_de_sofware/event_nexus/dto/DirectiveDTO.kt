package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.factory.DirectiveFactory
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectivePriority
import java.time.LocalDateTime

data class DirectiveDTO(
    val creatorId: Long,
    val creatorImage:String?,
    val name:String?,
    val title:String,
    val date: LocalDateTime?,
    val description:String,
    val priorityName: String
){
    fun toEntity(creatorEmployee: Employee): Directive{
        return DirectiveFactory().newDirective(
            creatorEmployee,
            titleDirective = title,
            descriptionInput = description,
            priorityInput = DirectivePriority.entries.find { it.priorityName == this.priorityName }!!
        )
    }
}


fun Directive.toDTO(): DirectiveDTO{
    return DirectiveDTO(
        creatorId = creator.id!!,
        creatorImage = creator.image,
        name = creator.name + " " + creator.lastname,
        title = this.title,
        date= this.date,
        description = this.description,
        priorityName = this.priority.priorityName
    )
}

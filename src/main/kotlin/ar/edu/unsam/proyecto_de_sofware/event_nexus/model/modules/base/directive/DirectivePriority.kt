package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive

enum class DirectivePriority(val priorityName:String) {
    HIGH(priorityName = "urgente"),
    MEDIUM(priorityName = "importante"),
    LOW(priorityName = "informativo")
}
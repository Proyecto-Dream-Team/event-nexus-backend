package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand

interface Person {
    var name:String
    var lastname:String
    val job:String
    var permissions: MutableSet<ModuleCommand>
    var active:Boolean

    fun executeModuleAction(command: ModuleCommand)
}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common

interface ModuleCommand{
    fun execute(userAllowedCommands: MutableSet<ModuleCommand>)
}
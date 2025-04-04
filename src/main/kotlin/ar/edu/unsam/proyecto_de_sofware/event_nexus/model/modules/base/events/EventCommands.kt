package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand

abstract class EventModuleCommand(val module: EventModule = EventModule()): ModuleCommand {
    abstract fun doExecute()

    abstract fun check(userAllowedCommands: MutableSet<ModuleCommand>)

    override fun execute(userAllowedCommands: MutableSet<ModuleCommand>) {
        check(userAllowedCommands)
        doExecute()
    }
}

class CreateEvent: EventModuleCommand() {
    override fun doExecute() {
        module.notifyEvent()
    }

    override fun check(userAllowedCommands: MutableSet<ModuleCommand>) {
        if(userAllowedCommands.filterIsInstance<CreateEvent>().isEmpty()){
            throw Exception("FALLIDO")
        }
    }
}

//class CancelEvent(): EventModuleCommand() {
//    override fun doExecute() {
//        module.cancelEvent()
//    }
//}
//
//class ScheduleEvent(): EventModuleCommand() {
//    override fun doExecute() {
//        module.scheduleEvent()
//    }
//}
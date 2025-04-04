package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

// ////////////////////////////////////////////////////////////
// MODULES
// ////////////////////////////////////////////////////////////
interface AppModule

class EventModule: AppModule{

    fun notifyEvent(){}

    fun cancelEvent(){}

    fun scheduleEvent(){}

}

// ////////////////////////////////////////////////////////////
//  MODULE COMMANDS
// ////////////////////////////////////////////////////////////


interface ModuleCommand{
    fun execute(userAllowedCommands: MutableSet<ModuleCommand>)
}

abstract class EventModuleCommand(val module:EventModule = EventModule()): ModuleCommand{
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


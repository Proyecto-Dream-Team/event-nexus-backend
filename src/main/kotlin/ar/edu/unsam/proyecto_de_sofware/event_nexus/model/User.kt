package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

class User{
    var userId:Int = 1
    var name:String = "Adrian"
    var lastname:String = "Perez"
    var active:Boolean = true

    public var allowedModuleCommand = mutableSetOf<ModuleCommand>()

    private lateinit var moduleAction:ModuleCommand

    fun setModuleAction(command: ModuleCommand){
        moduleAction = command
    }
    fun executeModuleAction(){
        moduleAction.execute(this.allowedModuleCommand)
    }
}




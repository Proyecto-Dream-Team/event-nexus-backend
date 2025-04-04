package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

open class User(var name: String, var lastname: String, role: Role){
    var userId: Long? = null
    var active: Boolean = true

    public var allowedModuleCommand = mutableSetOf<ModuleCommand>()

    private lateinit var moduleAction:ModuleCommand

    fun setModuleAction(command: ModuleCommand){
        moduleAction = command
    }
    fun executeModuleAction(){
        moduleAction.execute(this.allowedModuleCommand)
    }
}




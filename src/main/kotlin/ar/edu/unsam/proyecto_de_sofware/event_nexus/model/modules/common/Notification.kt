package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common


data class Notification(
    var id:Long? = null,
    var from:String,
    var date:String,
    var module:String,
    var readed:Boolean = false,
    var type:String
)
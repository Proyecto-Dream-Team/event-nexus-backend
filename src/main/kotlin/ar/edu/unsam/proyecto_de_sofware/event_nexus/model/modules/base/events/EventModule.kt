package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule

class EventModule: AppModule {
    val name: String = "Eventos"
    val img: String = ""
    fun notifyEvent(){}

    fun cancelEvent(){}

    fun scheduleEvent(){}

}
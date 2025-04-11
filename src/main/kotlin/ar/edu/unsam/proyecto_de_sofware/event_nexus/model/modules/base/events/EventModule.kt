package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule

class EventModule: AppModule {

    override val id: Int = 1
    override val name: String = "Eventos"
    override val image: String = "fotoEventosSinFondo.png"

    fun notifyEvent(){}

    fun cancelEvent(){}

    fun scheduleEvent(){}

}

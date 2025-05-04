package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule

class EventModule(
    override val id: Int = 1,
    override val name: String = "Events",
    override val image: String = "fotoEventosSinFondo.png"
) : AppModule(id, name, image){


}

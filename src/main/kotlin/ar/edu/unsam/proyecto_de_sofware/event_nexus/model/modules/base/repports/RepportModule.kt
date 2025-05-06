package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule

class RepportModule(
    override val id: Int = 3,
    override val name: String = "Reportes",
    override val image: String = "events.svg"
) : AppModule(id, name, image){
    override var description:String = "En este modulo podes crear eventos, unirte a los que te interesen o salir cuando lo desees. Gestiona tu participacion facilmente"
}

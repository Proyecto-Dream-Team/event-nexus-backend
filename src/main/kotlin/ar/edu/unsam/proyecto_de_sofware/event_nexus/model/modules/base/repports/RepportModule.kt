package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule

class RepportModule(
    name: String = "Informacion directiva",
    image: String = ""
) : AppModule(name, image) {

//    override val id: Int = 2
//    override val name: String = "Reporte de faltantes"
//    override val image: String = "objetosPerdidos.png"

    fun shortageNotification(){}

    fun replacementNotification(){}
}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule

class RepportModule: AppModule {

    override val id: Int = 2
    override val name: String = "Reporte de faltantes"
    override val image: String = ""

    fun shortageNotification(){}

    fun replacementNotification(){}
}
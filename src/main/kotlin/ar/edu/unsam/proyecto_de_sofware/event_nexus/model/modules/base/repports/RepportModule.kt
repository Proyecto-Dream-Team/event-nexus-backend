package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule

class RepportModule: AppModule {
    val name: String = "Reporte de faltantes"
    val img: String = ""

    fun shortageNotification(){}

    fun replacementNotification(){}
}
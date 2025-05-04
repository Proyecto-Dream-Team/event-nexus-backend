package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.admin

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule

class AdminModule(
    override val id: Int = 4,
    override val name: String = "Comunicarse con administrador",
    override val image: String = "fotoEventosSinFondo.png"
) : AppModule(id, name, image){}

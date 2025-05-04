package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule


class DirectiveModule(
    override val id: Int = 2,
    override val name: String = "Informacion directiva",
    override val image: String = "preguntasFrecuentes.png"
) : AppModule(id, name, image){}

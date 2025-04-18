package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import jakarta.persistence.Entity

@Entity
class DirectiveModule(
    name: String = "Informacion directiva",
    image: String = ""
) : AppModule(name, image) {

//    override val id: Int = 3
//    override val name: String = "Información y preguntas frecuentes"
//    override val image: String = "preguntasFrecuentes.png"

    fun mock(){}
}
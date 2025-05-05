package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule


class DirectiveModule(
    override val id: Int = 2,
    override val name: String = "Informacion directiva",
    override val image: String = "information.svg"
) : AppModule(id, name, image){
    override var description:String = "Modulo para informacion directiva dentro de la empresa. Actualizaciones, consulta y reportes de informacion directiva"

}

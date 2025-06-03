package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.admin

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule

class AdminModule(
    override val id: Int = 4,
    override val name: String = "Comunicarse con administrador",
    override val image: String = "admin.svg"
) : AppModule(id, name, image){

    override var description:String = "En este módulo podés crear nuevos usuarios, editar su información, eliminarlos si es necesario y ver todos los usuarios registrados. Gestioná fácilmente el acceso y la organización del sistema desde un solo lugar."

}

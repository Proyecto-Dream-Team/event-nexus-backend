package ar.edu.unsam.proyecto_de_sofware.event_nexus.dto

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule

data class ModuleDTO (
    val id: Int,
    val name: String,
    val image:String
){}


fun toModuleDTO(module: AppModule): ModuleDTO{
    return ModuleDTO(
        id = module.id,
        name = module.name,
        image = module.image
    )
}
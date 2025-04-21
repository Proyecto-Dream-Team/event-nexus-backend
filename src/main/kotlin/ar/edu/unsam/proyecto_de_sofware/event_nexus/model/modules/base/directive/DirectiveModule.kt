package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import jakarta.persistence.Entity

@Entity
class DirectiveModule: AppModule() {
    override var image: String = "imagen"

}
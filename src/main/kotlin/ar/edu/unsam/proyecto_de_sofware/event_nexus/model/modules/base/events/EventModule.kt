package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany


@Entity
class EventModule: AppModule(){
    override var image: String = "fotoEventosSinFondo.png"

    @OneToMany(mappedBy = "module")
    var events: MutableList<Event> = mutableListOf()

}

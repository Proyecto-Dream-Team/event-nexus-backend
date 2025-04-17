package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import org.springframework.boot.autoconfigure.security.SecurityProperties.User

class EventModule: AppModule {

    override val id: Int = 1
    override val name: String = "Eventos"
    override val image: String = "fotoEventosSinFondo.png"
    val events: List<Event> = mutableListOf()

    fun notifyEvent(){}

    fun cancelEvent(){}

    fun scheduleEvent(event: Event){
        events.addLast(event)
    }

    fun inviteFriends(user: Employee, event: Event){
        event.invite(user)
    }

}

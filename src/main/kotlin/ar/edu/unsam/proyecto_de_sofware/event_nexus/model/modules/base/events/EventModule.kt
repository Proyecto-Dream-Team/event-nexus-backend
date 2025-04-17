package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.springframework.boot.autoconfigure.security.SecurityProperties.User

@Entity
class EventModule: AppModule {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    val id: Long ? = null
    @Column(length = 40)

    override val name: String = "Eventos"

    @Column(length = 40)
    override val image: String = "fotoEventosSinFondo.png"

    @OneToMany
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

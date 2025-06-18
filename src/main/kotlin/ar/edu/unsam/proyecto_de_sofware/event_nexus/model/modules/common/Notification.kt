package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "notification")
data class Notification(
    @Id
    @GeneratedValue
    var id:Long? = null
){

    @ManyToOne
    lateinit var creator: Employee

    @Column
    lateinit var type:String

    @Column
    var date: LocalDateTime = LocalDateTime.now()

    @Column
    lateinit var title:String

    @Column
    var readed:Boolean = false

    @ManyToMany
    lateinit var listeners: MutableSet<Employee>

}
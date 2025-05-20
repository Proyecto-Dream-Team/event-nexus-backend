package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
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

    @OneToOne
    lateinit var creator: Employee

    @Column
    lateinit var type:String

    @Column
    var date: LocalDateTime = LocalDateTime.now()

    @Column
    var readed:Boolean = false

//    @ManyToMany
//    var listeners: Employee
}
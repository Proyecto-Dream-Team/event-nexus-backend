package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column
    var date: LocalDateTime = LocalDateTime.now()

    @Column
    var duration : Int = 0

    @Column
    var dateFinished: LocalDateTime = LocalDateTime.now()

    @Column(length = 255)
    var description: String = ""

//    @Column
//    @ManyToMany
//    @JoinColumn()
//    val guests : MutableSet<Employee> = mutableSetOf()


    fun isPendding() = dateFinished >= LocalDateTime.now()

//    fun invite(user: Employee){
//        guests.add(user)
//    }
}
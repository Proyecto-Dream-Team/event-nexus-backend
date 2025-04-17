package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Entity
class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null

    @Column
    val date: LocalDateTime = LocalDateTime.now()

    @Column
    val duration: Int = 0

    @Column
    val dateFinished = dateFinished()

    @Column(length = 255)
    val description: String = ""

    @Column
    @ManyToMany
    @JoinColumn()
    val guests : MutableSet<Employee> = mutableSetOf()

    fun dateFinished() = date.plus(duration.toLong(), ChronoUnit.MINUTES)

    fun isPendding() = dateFinished >= LocalDateTime.now()

    fun invite(user: Employee){
        guests.add(user)
    }
}
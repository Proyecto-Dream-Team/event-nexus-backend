package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "event")
class Event(){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.EAGER)
    var module: EventModule? = null

    @Column
    var name: String = ""

    @Column
    var creationDate: LocalDateTime = LocalDateTime.now()

    @Column
    lateinit var date: LocalDateTime

    @Column
    var dateFinished: LocalDateTime = LocalDateTime.now()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    lateinit var creator: Employee

    @Column
    var description: String = ""

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="employee_event",
        joinColumns=
            [JoinColumn(name="employee_id", referencedColumnName="id")],
        inverseJoinColumns=
            [JoinColumn(name="event_id", referencedColumnName="id")]
    )
    lateinit var participants: MutableSet<Employee>

    fun addParticipant(participant:Employee){
        this.participants.add(participant)
    }

    fun removeParticipant(participant:Employee){
        this.participants.remove(participant)
    }

    fun isPending() : Boolean = date > LocalDateTime.now()
}
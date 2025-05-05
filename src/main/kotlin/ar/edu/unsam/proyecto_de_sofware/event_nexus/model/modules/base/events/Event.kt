package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.EventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "event")
class Event(){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.EAGER)
    var module: EventModule? = null

    @Column
    var title: String = ""

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

    @Column
    var public: Boolean = false

    @Column
    @Enumerated(EnumType.STRING)
    var type: EventType = EventType.SOCIAL

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

    fun employeeParticipates(participant: Employee): Boolean{
        return  participants.contains(participant)
    }

    fun isPending() : Boolean = date > LocalDateTime.now()

    fun fromDTO(eventDTO: ShowEventDTO){
        if(!isCreator(eventDTO.creatorId!!)){
            throw BusinessException("No puede modificar este evento")
        }
        date = eventDTO.dateFinished
        description = eventDTO.description
        title = eventDTO.title
    }

    fun isCreator(employeeId: Long): Boolean{
        return employeeId == creator.id
    }
}
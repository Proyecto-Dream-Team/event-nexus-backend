package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ShowEventDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notifiable
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime

@Entity
@Table(name = "event")
class Event(): Notifiable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    var title: String = ""

    @Column
    var creationDate: LocalDateTime = LocalDateTime.now()

    @Column
    lateinit var expirationDate: LocalDateTime

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var creator: Employee

    @Column
    var description: String = ""

    @Column
    var public: Boolean = true

    @Column
    @Enumerated(EnumType.STRING)
    var type: EventType = EventType.SOCIAL

    @ManyToMany(
        fetch = FetchType.EAGER,
//        cascade = [CascadeType.REMOVE],

    )
    @JoinTable(
        name="employee_event",
        joinColumns=
            [JoinColumn(name="event_id", referencedColumnName="id")],
        inverseJoinColumns = [
            JoinColumn(
                name = "employee_id",
                referencedColumnName = "id",
                foreignKey = ForeignKey(
                    foreignKeyDefinition = "FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE"
                )
            )
        ]

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

    fun isPending() : Boolean = expirationDate > LocalDateTime.now()

    fun fromDTO(eventDTO: ShowEventDTO){
        if(!isCreator(eventDTO.creatorId!!)){
            throw BusinessException("No puede modificar este evento")
        }
        creationDate = eventDTO.dateFinished
        description = eventDTO.description
        title = eventDTO.title
    }

    fun isCreator(employeeId: Long): Boolean{
        return employeeId == creator.id
    }
}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notifiable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime

@Entity
@Table(name = "directive")
class Directive: Notifiable {

    @Id
    @GeneratedValue
    var id: Long? = null

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    lateinit var creator: Employee

    @Column
    lateinit var title: String

    @Column
    lateinit var description: String

    @Column
    @Enumerated(EnumType.STRING)
    var priority: DirectivePriority = DirectivePriority.LOW

    @Column
    var date: LocalDateTime = LocalDateTime.now()
}
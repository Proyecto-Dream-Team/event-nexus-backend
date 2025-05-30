package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notifiable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "directive")
class Directive: Notifiable {

    @Id
    @GeneratedValue
    var id: Long? = null

    @OneToOne
    lateinit var creator: Employee

    @Column
    lateinit var title: String

    @Column
    lateinit var descripcion: String

    @Column
    var date: LocalDateTime = LocalDateTime.now()
}
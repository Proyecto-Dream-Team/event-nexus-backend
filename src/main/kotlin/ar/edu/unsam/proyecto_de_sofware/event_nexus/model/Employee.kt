package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.DataUpdateProfileDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.persistence.*
import kotlin.jvm.Transient

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type") // PARA DATABASE
@JsonSubTypes(
    //PARA SERIALIZACION
    JsonSubTypes.Type(value = Admin::class, name = "Administrador"),
    JsonSubTypes.Type(value = Employee::class, name = "Empleado")
)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
open class Employee(){

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    var credentials: Authentication? = null

    @Column
    lateinit var name: String

    @Column
    lateinit var lastname: String

    @Column
    var image:String = "https://res.cloudinary.com/dumcjdzxo/image/upload/imgDefault_t0achq"

    @Column
    val job: String = Role.EMPLOYEE_WATCHER.jobName

    @Column
    var active: Boolean = true

    @Column
    var address: String = ""

    @Column
    var phone: String = ""

    @Column
    var email: String = ""


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="employee_module_command",
        joinColumns=
            [JoinColumn(name="employee_id", referencedColumnName="id")],
        inverseJoinColumns=
            [JoinColumn(name="module_command_id", referencedColumnName="id")]
    )
    lateinit var permissions: MutableSet<ModuleCommand>

    fun modules():Set<String>{
        return permissions.map{ it.module.name }.toSet()
    }

    fun updateProfile(data: DataUpdateProfileDTO) {
        email = data.email
        phone = data.phone
        address = data.address
    }


//
//    fun executeModuleAction(command: ModuleCommand) {
//        command.execute(this.permissions)
//    }
}




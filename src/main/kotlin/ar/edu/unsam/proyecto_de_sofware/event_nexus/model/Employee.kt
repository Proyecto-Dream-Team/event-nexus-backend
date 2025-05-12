package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.DataUpdateProfileDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.EditEmployeeDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.EmployeeDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.UserCreateDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.persistence.*

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type") // PARA DATABASE
@JsonSubTypes(
    //PARA SERIALIZACION
    JsonSubTypes.Type(value = Admin::class, name = "Administrador"),
    JsonSubTypes.Type(value = Employee::class, name = "Empleado")
)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
open class Employee() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    lateinit var credentials: Authentication

    @Column
    lateinit var name: String

    @Column
    lateinit var lastname: String

    @Column
    var image: String = "https://res.cloudinary.com/dumcjdzxo/image/upload/imgDefault_t0achq"

    @Column
    lateinit var job: String

    @Column
    var active: Boolean = true

    @Column
    lateinit var address: String

    @Column
    lateinit var phone: String

    @Column
    lateinit var email: String


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    lateinit var permissions: MutableSet<Permission>

    fun updateProfile(data: DataUpdateProfileDTO) {
        email = data.email
        phone = data.phone
        address = data.address
    }

    fun editFromAdmin(editEmployeeDTO: EditEmployeeDTO) {
        name = editEmployeeDTO.name
        lastname = editEmployeeDTO.lastName
        phone = editEmployeeDTO.phone
        address = editEmployeeDTO.address
        email = editEmployeeDTO.email
        permissions.addAll(editEmployeeDTO.permissions)

    }
}




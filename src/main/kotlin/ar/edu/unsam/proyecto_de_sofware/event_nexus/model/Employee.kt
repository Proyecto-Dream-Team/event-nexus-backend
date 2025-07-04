package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.DataUpdateProfileDTO
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
    lateinit var credentials: Credentials

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

    @Column(unique = true)
    lateinit var email: String

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    lateinit var permissions: MutableSet<Permission>

    fun updateProfile(data: DataUpdateProfileDTO) {
        email = data.email
        phone = data.phone
        address = data.address
    }

    fun fullName():String = (this.name).plus(" ${this.lastname}")
    fun editFromAdmin(userCreateDTO: UserCreateDTO) {
        name = userCreateDTO.name
        lastname = userCreateDTO.lastName
        phone = userCreateDTO.phone
        address = userCreateDTO.address
        email = userCreateDTO.email
        permissions = Permission.entries.filter { userCreateDTO.permissions.contains(it.permissionName) }.toMutableSet()
        job = userCreateDTO.role.name //TODO arreglar futuro duplicidad de campos
        credentials.role = userCreateDTO.role
    }

}




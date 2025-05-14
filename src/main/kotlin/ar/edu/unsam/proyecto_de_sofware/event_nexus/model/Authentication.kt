package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import jakarta.persistence.*

@Entity
class Authentication{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(length = 100)
    lateinit var username: String

    @Column(length = 100)
    lateinit var password: String

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    lateinit var role: Role

    fun validateRegister(): Boolean {
        return password == "back" && username == "back"
    }

    fun setNewCredentials(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun validateUsername(username: String): Boolean {
        return  username == this.username
    }


}


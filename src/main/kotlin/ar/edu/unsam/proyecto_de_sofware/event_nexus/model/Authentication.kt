package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import jakarta.persistence.*

@Entity
class Authentication{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(length = 100)
    lateinit var email:String

    @Column(length = 100)
    lateinit var username: String

    @Column(length = 100)
    lateinit var password: String

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    lateinit var role: Role
}


package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
interface AppModule{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?
    val name:String
    val image:String
}


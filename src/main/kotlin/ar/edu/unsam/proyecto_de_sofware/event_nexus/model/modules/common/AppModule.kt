package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

interface AppModuleInterface{
    var id:Long?
    var name:String
    var image:String
}

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
abstract class AppModule:AppModuleInterface{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id:Long? = null

    @Column
    override lateinit var name: String

    @Column
    override lateinit var image: String
}



package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
abstract class AppModule(
    @Transient
    var name:String,

    @Column
    var image:String
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
//    var commands: MutableSet<ModuleCommand> = mutableSetOf()
}


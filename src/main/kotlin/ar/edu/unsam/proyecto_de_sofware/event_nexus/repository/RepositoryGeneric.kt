package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.User
import org.springframework.stereotype.Component
import java.time.LocalDateTime

interface AvaliableInstance {
    var id: Int
}

@Component
abstract class Repository<T : AvaliableInstance> {
    var instances: MutableSet<T> = mutableSetOf()

    fun getAll(): List<T> {
        return this.instances.toList()
    }

    fun create(objeto: T): Boolean {
        this.asignarID(objeto)
        return instances.add(objeto) //SI es Set devuelve true o false, si es list siempre devuelve true
    }

    fun delete(objeto: T) {
        exist(objeto.id)
        instances.removeIf { objeto.id == it.id }//
    }

    fun exist(id: Int) {
        if (!existeElemento(id)) {
            throw Exception("no se encuentra")
        }
    }

    private fun existeElemento(id: Int) = instances.any { it.id == id }

    fun update(objeto: T) {
        delete(objeto)
        instances.add(objeto)
    }

    fun getByID(objectID: Int): T {
        exist(objectID)
        return instances.first { it.id == objectID } //devuelve elemento o null
    }


    private fun asignarID(objeto: T) {
        val lastId = instances.maxOfOrNull { it.id }
        objeto.id = if(lastId != null) lastId + 1 else 1
    }

}

@Component
class AuthRepository(): Repository<Authentication>() {

    fun findByUsername(username:String): Authentication?{
        return instances.find { it.username == username }
    }


}

@Component
class UserRepository(): Repository<User>() {

}

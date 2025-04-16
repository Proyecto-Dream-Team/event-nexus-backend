package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val repoUser: UserRepository) {

    fun getByID(id : Long): Employee {
        val user = repoUser.findById(id)
        return user.get()
    }

    fun getUser(id : Int): Employee {
        val user = repoUser.findById(id.toLong())
        return user.get()
    }

}
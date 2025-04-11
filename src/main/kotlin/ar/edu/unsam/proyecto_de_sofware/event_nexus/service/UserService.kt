package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.User
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.UserNew
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val repoUser: UserRepository) {

    fun getUser(id : Int): UserNew {
        val user = repoUser.findById(id.toLong())
        return user.get()
    }

}
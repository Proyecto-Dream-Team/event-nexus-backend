package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class MockService(
    val authRepository: AuthRepository,
    val userRepository: UserRepository,
    ) {

    fun getUser(id:Long):Employee{
        return userRepository.findById(id).get()
    }

    fun getByType(type:String):List<Employee>{
        return userRepository.findWithType(type)
    }


    fun getAdmin(id:Long): Admin?{
        val user = userRepository.findById(id).get()
        if(user !is Admin){
            throw BusinessException("")
        }
        return user
//        return if(user is Admin){
//            return user
//        }else{
//            null
//        }
    }

}
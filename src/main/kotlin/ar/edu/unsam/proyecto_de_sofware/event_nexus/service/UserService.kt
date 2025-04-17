package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.DataUpdateProfileDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ImgDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(val repoUser: UserRepository) {

    fun getByID(id : Long): Employee {
        val user = repoUser.findById(id)
        return user.get()
    }

    @Transactional
    fun updateProfile(dataUpdateProfileDTO: DataUpdateProfileDTO) : ResponseEntity<String> {
        val user = repoUser.findById(dataUpdateProfileDTO.id).get()
        user.updateProfile(dataUpdateProfileDTO)
        try {
            repoUser.save(user)
        }catch (e: DataAccessException){
            throw DataBaseNotModifiedException("No se pudo actualizar el perfil")
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Actualizacion exitosa!")
    }

    @Transactional
    fun changeImg(user: Employee, img: ByteArray): ResponseEntity<String> {

        try {
            repoUser.save(user)
        }catch (error: DataAccessException){
            throw DataBaseNotModifiedException("No se pudo actualizar el perfil")
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Actualizacion exitosa!")
    }

}
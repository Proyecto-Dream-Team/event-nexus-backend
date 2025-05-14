package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.DataUpdateProfileDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.ImgDTO
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.HttpClientErrorException.NotFound

@Service
class UserService(val repoUser: UserRepository) {

    fun getByID(id : Long): Employee {
        return repoUser.findById(id).orElseThrow{throw BusinessException("Usuario no encontrado")}
    }

    @Transactional
    fun updateProfile(dataUpdateProfileDTO: DataUpdateProfileDTO) {
        val user = repoUser.findById(dataUpdateProfileDTO.id).get()
        user.updateProfile(dataUpdateProfileDTO)
        try {
            repoUser.save(user)
        } catch (e: DataAccessException) {
            throw DataBaseNotModifiedException("No se pudo actualizar el perfil")
        }
    }

    @Transactional
    fun changeImg(user: Employee, img: String){
        user.image = img
        try {
            repoUser.save(user)
        }catch (error: DataAccessException){
            throw DataBaseNotModifiedException("No se pudo actualizar el perfil")
        }
    }

    @Transactional
    fun saveEmployee(employee: Employee): Employee {
        return try {
            repoUser.save(employee)
        } catch (e: DataAccessException) {
            throw RuntimeException("No se pudo actualizar eventos")
        }
    }
    fun findAllById(employeesIds: List<Long>): List<Employee> {
        return repoUser.findAllById(employeesIds).toList()
    }

    @Transactional
    fun create(employee: Employee) {
        try{
            repoUser.save(employee)
        }catch (e: DataAccessException){
            throw DataBaseNotModifiedException("No se pudo crear Usuario")
        }
    }

    @Transactional
    fun edit(employee: Employee) {
        try {
            repoUser.save(employee)
        }catch (e: DataAccessException){
            throw DataBaseNotModifiedException("No se pudo modificar la informacion del usuario")
        }
    }

    fun gerPermissions(id: Long): List<Permission> {
        return repoUser.findById(id).get().permissions.toList()
    }

}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginResponse
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.NewAccountRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.admin.AdminModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.dao.DataAccessException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(val authRepository: AuthRepository, val userRepository: UserRepository) {

    val adminModule: AdminModule = AdminModule()

    @Transactional
    fun createAccount(newAccountData: NewAccountRequest, admin:Admin){
        val newAccount:Authentication = admin.createAccount(
            username = newAccountData.username,
            password = newAccountData.password,
            email = newAccountData.email,
            role = Role.entries.first { it.jobName == newAccountData.jobName }
        )
        try {
            authRepository.save(newAccount)
        } catch (e: DataAccessException) {
            throw RuntimeException("No se pudo crear cuenta")
        }
    }

    @Transactional
    fun login(loginRequest: LoginRequest):LoginResponse{
        val existingUser: Authentication? = authRepository.findByUsername(loginRequest.username)
        if(existingUser!!.password != loginRequest.password){
            throw BusinessException("Invalid credentials")
        }
        val existingUserId = requireNotNull(existingUser!!.id) { "Client ID is null" }
        val user = userRepository.findByCredentials_Id(existingUserId)
        return LoginResponse(user.id!!, existingUser!!.role.jobName, user.image)
    }

    fun getAdminById(adminId:Long):Admin{
        val user = userRepository.findById(adminId).get()
        if(user !is Admin){
            throw BusinessException("El usuario no es administrador")
        }
        return user
    }

    fun checkExistintUsername(username:String){
        val existingUser: Authentication? = authRepository.findByUsername(username=username)
        if(existingUser != null){
            throw BusinessException("Existing credentials")
        }
    }

    fun checkPermission(employee: Employee, permission: Permission){
        this.adminModule.checkPermission(employeePermmission = employee.permissions, permissionToCheck = permission)
    }

}
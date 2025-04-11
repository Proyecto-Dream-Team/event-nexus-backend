package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginResponse
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.NewAccountRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import org.springframework.stereotype.Service

@Service
class AuthService(val authRepository: AuthRepository) {

    fun mockGetAll():List<Authentication>{
        return authRepository.findAll().toList()
    }

    fun createAccount(newAccountData: NewAccountRequest){
        val existingUser = authRepository.findByUsername(newAccountData.username)
        if(existingUser != null){
            throw BusinessException("Existing credentials")
        }
        val admin: Admin = Admin() //Aca deberia obtenerse del repositorio de admins
        val newAccount:Authentication = admin.createAccount(
            username = newAccountData.username,
            password = newAccountData.password,
            email = newAccountData.email,
            role = Role.values().first { it.jobName == newAccountData.jobName }
        )
        authRepository.save(newAccount)
    }

    fun login(loginRequest: LoginRequest):LoginResponse{
        val existingUser = authRepository.findByUsername(loginRequest.username)
        if(existingUser.password != loginRequest.password){
            throw BusinessException("Invalid credentials")
        }
        return LoginResponse(existingUser.id!!, existingUser.role.jobName)
    }
}
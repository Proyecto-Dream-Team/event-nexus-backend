package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginResponse
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.NewAccountRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.DataBaseNotModifiedException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.InvalidCredentialsException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.NotFoundException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.admin.AdminModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.dao.DataAccessException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    val authRepository: AuthRepository,
    val passwordEncoder: PasswordEncoder
): UserDetailsService {

    val adminModule: AdminModule = AdminModule()

    @Transactional
    fun createAccount(newAccountData: NewAccountRequest, admin:Admin){
        val newAccount:Credentials = admin.createAccount(
            username = newAccountData.username,
            password = passwordEncoder.encode(newAccountData.password),
            email = newAccountData.email,
            role = Role.entries.first { it.jobName == newAccountData.jobName }
        )
        try {
            authRepository.save(newAccount)
        } catch (e: DataAccessException) {
            throw RuntimeException("No se pudo crear cuenta")
        }
    }

//    @Transactional
//    fun login(loginRequest: LoginRequest):LoginResponse{
//        val existingUser: Credentials? = authRepository.findByUsername(loginRequest.username)
//        if(existingUser!!.password != loginRequest.password){
//            throw BusinessException("Invalid credentials")
//        }
//        val existingUserId = requireNotNull(existingUser!!.id) { "Client ID is null" }
//        val user = userRepository.findByCredentials_Id(existingUserId)
//        return LoginResponse(user.id!!, existingUser!!.role.jobName, user.image)
//    }



    fun checkExistintUsername(username:String){
        val existingUser: Credentials? = authRepository.findByUsername(username=username)
        if(existingUser != null){
            throw BusinessException("Existing credentials")
        }
    }

    fun checkPermission(employee: Employee, permission: Permission){
        this.adminModule.checkPermission(employeePermmission = employee.permissions, permissionToCheck = permission)
    }


    fun encode(password:String):String{
        return this.passwordEncoder.encode(password)
    }
    @Transactional
    fun update(credential: Credentials) {
        try{
            authRepository.save(credential)
        }catch (e: DataAccessException){
            throw DataBaseNotModifiedException("No se pudieron crear credenciales")
        }
    }

    fun checkCredentialsBack(username: String, password: String): Boolean {
        return username == "back" && password == "back"
    }

    override fun loadUserByUsername(username: String?): UserDetails? {
        if(username == null) throw InvalidCredentialsException("Usuario o Contraseña Incorrecta 0")
        val user = this.findUserByUsername(username=username)
            ?: throw InvalidCredentialsException("Usuario o Contraseña Incorrecta 1")
        return  user
    }

    fun findUserByUsername(username: String): Credentials? {
        return authRepository.findByUsername(username)
    }

    fun validPassword(password: String, user: UserDetails){
        if(!passwordEncoder.matches(password, user.password)){
            throw InvalidCredentialsException("Usuario o contraseña incorrecta 2")
        }
    }

    fun authenticate(user: Credentials) : Authentication{
        return UsernamePasswordAuthenticationToken(user.username, user.password)
    }

    fun setContext(authorizedUser: Authentication) {
        SecurityContextHolder.getContext().authentication = authorizedUser
    }
}
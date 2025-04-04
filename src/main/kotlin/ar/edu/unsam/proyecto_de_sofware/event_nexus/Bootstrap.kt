package ar.edu.unsam.proyecto_de_sofware.event_nexus


import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random

@Component
class Bootstrap(
    @Autowired val authRepo: AuthRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        createAccounts()
    }

    fun createAccounts(){
        val admin:Admin = Admin()
        val account01:Authentication = admin.createAccount(
            username = "simple",
            password = "simple",
            email = "simple@simple",
            role = Role.EMPLOYEE_SIMPlE
        )
        val account02:Authentication = admin.createAccount(
            username = "full",
            password = "full",
            email = "full@full",
            role = Role.EMPLOYEE_FULL
        )
        val account03:Authentication = admin.createAccount(
            username = "admin",
            password = "admin",
            email = "admin@admin",
            role = Role.ADMIN
        )
        val accounts: List<Authentication> = listOf(account01, account02, account03)
        accounts.forEach { authRepo.create(it) }
    }

}
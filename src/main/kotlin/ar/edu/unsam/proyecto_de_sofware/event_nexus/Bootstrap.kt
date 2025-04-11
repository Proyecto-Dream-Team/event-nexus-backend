package ar.edu.unsam.proyecto_de_sofware.event_nexus


import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports.RepportModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.space_reservations.SpaceReservationsModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


@Component
class Bootstrap(
    @Autowired val authRepo: AuthRepository,
    @Autowired val userRepo: UserRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        createAccounts()
        createUsers()
    }

    fun createAccounts(){
        val admin:Admin = Admin()
        val account01:Authentication = admin.createAccount(
            username = "adrian",
            password = "adrian",
            email = "adrian@mail.com",
            role = Role.EMPLOYEE_SIMPLE
        )
        val account02:Authentication = admin.createAccount(
            username = "diego",
            password = "diego",
            email = "diego@mail.com",
            role = Role.EMPLOYEE_FULL
        )
        val account03:Authentication = admin.createAccount(
            username = "matias",
            password = "matias",
            email = "matias@mail.com",
            role = Role.ADMIN
        )
        val accounts: List<Authentication> = listOf(account01, account02, account03)
        authRepo.saveAll(accounts)
    }

    fun createUsers () {
//        ADMINS
        val admin = AdminNew().apply {
            phone = "12341234"
            email = "perez.A@gmail.com"
            address = "calle posta 123"
        }


//        USERS
        val diego = UserNew().apply {
            phone = "12341234"
            email = "diego.lentz@gmail.com"
            address = "calle falsa 123"
//            modules = listOf(EventModule(), DirectiveModule())
        }

        val pedro = UserNew().apply {
            phone = "12341234"
            email = "pedrito@gmail.com"
            address = "Tambien calle falsa 123"
//            modules = listOf(EventModule(), DirectiveModule(), RepportModule(), SpaceReservationsModule())
        }

        val users : List<UserNew> = listOf( pedro, diego, admin)
        userRepo.saveAll(users)
    }

}
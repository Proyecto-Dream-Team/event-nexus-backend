package ar.edu.unsam.proyecto_de_sofware.event_nexus


import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.User
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
            username = "simple",
            password = "simple",
            email = "simple@simple",
            role = Role.EMPLOYEE_SIMPLE
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
        authRepo.saveAll(accounts)
    }

    fun createUsers () {
        val admin = Admin().apply {
            name = "Adrian"
            lastname = "Perez"
            phone = "12341234"
            email = "perez.A@gmail.com"
            address = "calle posta 123"
            img = "https://discord.com/channels/968129182246789160/1349492293639340074/1360075904742719659"

        }
        val userDiego = User().apply {
            name = "Diego"
            lastname = "Lentz"
            phone = "12341234"
            email = "diego.lentz@gmail.com"
            address = "calle falsa 123"
            modules = listOf(EventModule(), DirectiveModule(), RepportModule(), SpaceReservationsModule())
            img = "https://discord.com/channels/968129182246789160/1349492293639340074/1360075904742719659"

        }

        val userPedro = User().apply {
            name = "Pedro"
            lastname = "Mc Gregor"
            phone = "12341234"
            email = "pedrito@gmail.com"
            address = "Tambien calle falsa 123"
            modules = listOf(EventModule(), DirectiveModule())
            img = "https://discord.com/channels/968129182246789160/1349492293639340074/1360075904742719659"
        }

        val users : List<User> = listOf( userPedro, userDiego)
        users.forEach{
                userRepo.create(it)
            }
    }

}
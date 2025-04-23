package ar.edu.unsam.proyecto_de_sofware.event_nexus


import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.SendDirective
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CancelEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.CreateEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.ScheduleEvent
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports.RepportModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.space_reservations.SpaceReservationsModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.CommandRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.net.URL
import javax.print.DocFlavor


@Component
class Bootstrap(
    @Autowired val authRepo: AuthRepository,
    @Autowired val userRepo: UserRepository,
    @Autowired val commandRepo: CommandRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        createAccounts()
        createUsers()
        createCommands()
        addPermissions()
    }

    fun createAccounts() {
        val account01: Authentication = Authentication().apply {
            username = "adrian"
            password = "adrian"
            email = "adrian@mail.com"
            role = Role.EMPLOYEE_FULL
        }
        val account02: Authentication = Authentication().apply {
            username = "diego"
            password = "diego"
            email = "diego@mail.com"
            role = Role.EMPLOYEE_FULL
        }
        val account03: Authentication = Authentication().apply {
            username = "matias"
            password = "matias"
            email = "matias@mail.com"
            role = Role.EMPLOYEE_FULL
        }

        val account04: Authentication = Authentication().apply {
            username = "valen"
            password = "valen"
            email = "valen@mail.com"
            role = Role.ADMIN
        }
        val accounts: List<Authentication> = listOf(account01, account02, account03, account04)
        authRepo.saveAll(accounts)
    }

    fun createUsers() {
// //////////////////////////////////////////////////////////////////////
//        ADMINS
// //////////////////////////////////////////////////////////////////////

        val credential1 = authRepo.findById(4).get()
        val admin = Admin().apply {
            name = "Valentin"
            lastname = "Pugliese"
            phone = "12341234"
            email = "valentin.d@gmail.com"
            address = "calle posta 123"
            credentials = credential1
        }

// //////////////////////////////////////////////////////////////////////

// //////////////////////////////////////////////////////////////////////
//        USERS
// //////////////////////////////////////////////////////////////////////
        val credential2 = authRepo.findById(2).get()
        val diego = Employee().apply {
            name = "Diego"
            lastname = "Lentz"
            phone = "12341234"
            email = "diego.lentz@gmail.com"
            address = "calle falsa 123"
            credentials = credential2

        }

        val credential3 = authRepo.findById(3).get()
        val pedro = Employee().apply {
            name = "Pedro"
            lastname = "McGeraghty"
            phone = "12341234"
            email = "pedrito@gmail.com"
            address = "Tambien calle falsa 123"
            credentials = credential3

        }

        val credential4 = authRepo.findById(1).get()
        val adri = Employee().apply {
            name = "Adri"
            lastname = "Perez"
            phone = "12341234"
            email = "adrian@gmail.com"
            address = "calle falsa 123"
            credentials = credential4

        }
// //////////////////////////////////////////////////////////////////////
        val users: List<Employee> = listOf(pedro, diego, admin, adri)
        userRepo.saveAll(users)
    }

    fun createCommands() {
// //////////////////////////////////////////////////////////////////////
//        Event Commands
// //////////////////////////////////////////////////////////////////////
        val createEventCommand = CreateEvent()
        val cancelEventCommand = CancelEvent()
        val scheduleEventCommand = ScheduleEvent()
// //////////////////////////////////////////////////////////////////////


// //////////////////////////////////////////////////////////////////////
//        Directive Commands
// //////////////////////////////////////////////////////////////////////
        val sendDirectiveCommand = SendDirective()
// //////////////////////////////////////////////////////////////////////

        val commands: List<ModuleCommand> = listOf(
            createEventCommand, cancelEventCommand, scheduleEventCommand,
            sendDirectiveCommand
        )
        commandRepo.saveAll(commands)
    }

    fun addPermissions() {
        val admins: Employee
        val admin: Admin = Admin()

        val commands = commandRepo.findAll()


        val user4 = userRepo.findById(4).get()
        admin.addPermissions(user4, commands.toSet())

        val user3 = userRepo.findById(3).get()
        admin.addPermissions(user3, commands.toSet())

        val user2 = userRepo.findById(2).get()
        admin.addPermissions(user2, commands.toSet())

        val user1 = userRepo.findById(1).get()
        admin.addPermissions(user1, commands.toSet())


        val users = mutableListOf(user1, user2, user3, user4)
        userRepo.saveAll(users)
    }
}
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


@Component
class Bootstrap(
    @Autowired val authRepo: AuthRepository,
    @Autowired val userRepo: UserRepository,
    @Autowired val commandRepo:CommandRepository
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
            role = Role.EMPLOYEE_SIMPLE
        }
        val account02: Authentication = Authentication().apply {
            username = "diego"
            password = "diego"
            email = "diego@mail.com"
            role = Role.EMPLOYEE_FULL
        }
        val account03:Authentication = Authentication().apply {
            username = "matias"
            password = "matias"
            email = "matias@mail.com"
            role = Role.ADMIN
        }
        val accounts: List<Authentication> = listOf(account01, account02, account03)
        authRepo.saveAll(accounts)
    }

    fun createUsers () {
// //////////////////////////////////////////////////////////////////////
//        ADMINS
// //////////////////////////////////////////////////////////////////////
        val admin = Admin().apply {
            name = "Adrian"
            lastname = "Perez"
            phone = "12341234"
            email = "perez.A@gmail.com"
            address = "calle posta 123"
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5gv6VVdtAGLqBK9MXIBOUGJ-hWeVdiiN-3Q&s"

        }

// //////////////////////////////////////////////////////////////////////

// //////////////////////////////////////////////////////////////////////
//        USERS
// //////////////////////////////////////////////////////////////////////
        val diego = Employee().apply {
            name = "Diego"
            lastname = "Lentx"
            phone = "12341234"
            email = "diego.lentx@gmail.com"
            address = "calle falsa 123"
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5gv6VVdtAGLqBK9MXIBOUGJ-hWeVdiiN-3Q&s"


        }

        val pedro = Employee().apply {
            name = "Pedro"
            lastname = "McGeraghty"
            phone = "12341234"
            email = "pedrito@gmail.com"
            address = "Tambien calle falsa 123"

            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5gv6VVdtAGLqBK9MXIBOUGJ-hWeVdiiN-3Q&s"

        }
// //////////////////////////////////////////////////////////////////////
        val users : List<Employee> = listOf( pedro, diego, admin)
        userRepo.saveAll(users)
    }

    fun createCommands () {
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

        val commands : List<ModuleCommand> = listOf(
            createEventCommand, cancelEventCommand, scheduleEventCommand,
            sendDirectiveCommand
        )
        commandRepo.saveAll(commands)
    }

    fun addPermissions () {
        val admins:Employee
        val admin:Admin = Admin()

        val commands = commandRepo.findAll()


        val user = userRepo.findById(1).get()
        admin.addPermissions(user, commands.toSet())

        userRepo.save(user)
    }
}
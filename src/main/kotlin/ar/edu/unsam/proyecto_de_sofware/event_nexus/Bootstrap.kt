package ar.edu.unsam.proyecto_de_sofware.event_nexus


import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.SendDirective
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.*
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.ModuleCommand
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime


@Component
class Bootstrap(
    @Autowired val authRepo: AuthRepository,
    @Autowired val userRepo: UserRepository,
    @Autowired val commandRepo: CommandRepository,
    @Autowired val moduleRepo: AppModuleRepository,
    @Autowired val eventRepo: EventRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        createAccounts()
        createUsers()
        createModules()
//        createCommands()
        addPermissions()
        createEvents()
    }

    fun createAccounts() {
        val account01: Authentication = Authentication().apply {
            username = "adrian"
            password = "adrian"
            email = "adrian@mail.com"
            role = Role.ADMIN
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
            role = Role.ADMIN
        }
        val account04: Authentication = Authentication().apply {
            username = "pica"
            password = "pica"
            email = "pica@mail.com"
            role = Role.ADMIN
        }
        val account05: Authentication = Authentication().apply {
            username = "valen"
            password = "valen"
            email = "valen@mail.com"
            role = Role.ADMIN
        }
        val accounts: List<Authentication> = listOf(account01, account02, account03, account04, account05)
        authRepo.saveAll(accounts)
    }

    fun createUsers() {
        // //////////////////////////////////////////////////////////////////////
        //        ADMINS
        // //////////////////////////////////////////////////////////////////////

        val credentialsAdrian = authRepo.findByEmail("adrian@mail.com")
        val admin = Admin().apply {
            name = "Adrian"
            lastname = "Perez"
            phone = "12341234"
            email = credentialsAdrian.email
            address = "calle posta 123"
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5gv6VVdtAGLqBK9MXIBOUGJ-hWeVdiiN-3Q&s"
            credentials = credentialsAdrian
            job = "asdasdas"
        }

        // //////////////////////////////////////////////////////////////////////

        // //////////////////////////////////////////////////////////////////////
        //        USERS
        // //////////////////////////////////////////////////////////////////////
        val credential2 = authRepo.findByEmail("diego@mail.com")
        val diego = Employee().apply {
            name = "Diego"
            lastname = "Lentz"
            phone = "12341234"
            email = credential2.email
            address = "calle falsa 123"
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5gv6VVdtAGLqBK9MXIBOUGJ-hWeVdiiN-3Q&s"
            credentials = credential2

        }

        val credential3 = authRepo.findByEmail("pica@mail.com")
        val pedro = Employee().apply {
            name = "Pedro"
            lastname = "McGeraghty"
            phone = "12341234"
            email = credential3.email
            address = "Tambien calle falsa 123"
            credentials = credential3
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5gv6VVdtAGLqBK9MXIBOUGJ-hWeVdiiN-3Q&s"

        }
// //////////////////////////////////////////////////////////////////////
        val users: List<Employee> = listOf(pedro, diego, admin)
        userRepo.saveAll(users)
    }

    fun createModules() {
        val eventModule: EventModule = EventModule()
        val directiveModule: DirectiveModule = DirectiveModule()
//        val repportModule: RepportModule = RepportModule()
//        val reservationModule: SpaceReservationsModule = SpaceReservationsModule()

        val modules: List<AppModule> = listOf(eventModule, directiveModule)
        moduleRepo.saveAll(modules)

//        createCommands()

        val createEventCommand = CreateEvent().apply { module = eventModule }
        val cancelEventCommand = CancelEvent().apply { module = eventModule }
        val scheduleEventCommand = ScheduleEvent().apply { module = eventModule }
        val sendDirectiveCommand = SendDirective().apply { module = directiveModule }

        val commands: List<ModuleCommand> = listOf(
            createEventCommand, cancelEventCommand, scheduleEventCommand,
            sendDirectiveCommand
        )
        commandRepo.saveAll(commands)
    }


    fun createEvents() {
        val event1 = Event().apply {
            duration = 300
            date = LocalDateTime.now()
            dateFinished = LocalDateTime.now().plusDays(1)
            description = "Evento de prueba"
        }

        eventRepo.save(event1)
    }

    private fun createCommands() {
//        val eventModule:EventModule = moduleRepo.findEventModule()
//        val directiveModule: DirectiveModule = moduleRepo.findDirectiveModule()
//        // //////////////////////////////////////////////////////////////////////
//        //        Event Commands
//        // //////////////////////////////////////////////////////////////////////
//        val createEventCommand = CreateEvent(eventModule)
//        val cancelEventCommand = CancelEvent(eventModule)
//        val scheduleEventCommand = ScheduleEvent(eventModule)
//        // //////////////////////////////////////////////////////////////////////
//
//
//        // //////////////////////////////////////////////////////////////////////
//        //        Directive Commands
//        // //////////////////////////////////////////////////////////////////////
//        val sendDirectiveCommand = SendDirective(directiveModule)
//        // //////////////////////////////////////////////////////////////////////
//
//        val commands : List<ModuleCommand> = listOf(
//            createEventCommand, cancelEventCommand, scheduleEventCommand,
//            sendDirectiveCommand
//        )
//        commandRepo.saveAll(commands)
    }

    fun addPermissions() {
        val adminMock: Admin = Admin()

        val commands = commandRepo.findAll()

        val adrian: Employee = userRepo.findByEmail("adrian@mail.com")
        val diego: Employee = userRepo.findByEmail("diego@mail.com")
        val pica: Employee = userRepo.findByEmail("pica@mail.com")

        adminMock.addPermissions(employee = adrian, permissions = commands.toSet())
        adminMock.addPermissions(employee = diego, permissions = commands.take(2).toSet())
        adminMock.addPermissions(employee = pica, permissions = commands.take(1).toSet())


        userRepo.saveAll(listOf(adrian, diego, pica))
    }
}
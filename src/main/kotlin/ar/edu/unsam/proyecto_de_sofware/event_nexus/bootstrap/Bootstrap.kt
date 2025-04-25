package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.EventRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.ModuleRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Bootstrap(
    @Autowired val authRepo: AuthRepository,
    @Autowired val userRepo: UserRepository,
    @Autowired val moduleRepo: ModuleRepository,
    @Autowired val eventRepo: EventRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        createAccounts()
        createUsers()
        createModules()
        createModulesUsers()
        createEvents()
    }

    fun createAccounts() {
        val adrian: Authentication = credentialsAdrian()
        val diego: Authentication = credentialsDiego()
        val matias: Authentication = credentialsMatias()
        val pica: Authentication = credentialsPica()
        val valen: Authentication = credentialsValen()
        val theo: Authentication = credentialsTheo()
        val accounts: List<Authentication> = listOf(adrian, diego, matias, pica, valen, theo)
        authRepo.saveAll(accounts)
    }

    fun createUsers () {

        var credential: Authentication = authRepo.findByUsername(username = "adrian")
        val adrian = employeeAccountAdrian(credential)

        credential = authRepo.findByUsername(username = "diego")
        val diego = employeeAccountDiego(credential)

        credential = authRepo.findByUsername(username = "pica")
        val pica = employeeAccountPica(credential)

        credential = authRepo.findByUsername(username = "mati")
        val mati = employeeAccountMati(credential)

        credential = authRepo.findByUsername(username = "valen")
        val valen = employeeAccountValen(credential)

        credential = authRepo.findByUsername(username = "theo")
        val theo = employeeAccountTheo(credential)

        val users : List<Employee> = listOf(adrian, diego, pica, mati, valen, theo)
        userRepo.saveAll(users)
    }

    fun createModules () {

        var eventModule: AppModule = EventModule().apply {
            name = "Events"
            image = "fotoEventosSinFondo.png"
        }
        var directiveModule: AppModule = DirectiveModule().apply {
            name = "Directives"
            image = "preguntasFrecuentes.png"
        }
        moduleRepo.saveAll(listOf(
            eventModule, directiveModule
        ))
    }

    fun createModulesUsers () {
        var adrian: Employee = userRepo.findByEmail(email = "adrian@mail.com")
        var diego: Employee = userRepo.findByEmail(email = "diego@mail.com")
        var pica: Employee = userRepo.findByEmail(email = "pica@mail.com")
        var mati: Employee = userRepo.findByEmail(email = "mati@mail.com")
        var valen: Employee = userRepo.findByEmail(email = "valen@mail.com")
        var theo: Employee = userRepo.findByEmail(email = "theo@mail.com")

        var eventModule: AppModule = moduleRepo.findByName(name = "Eventos")
        var directiveModule: AppModule = moduleRepo.findByName(name = "Informacion directiva")

        adrian.modules.addAll(elements = listOf(eventModule, directiveModule))
        diego.modules.addAll(elements = listOf(eventModule))
        pica.modules.addAll(elements = listOf(eventModule, directiveModule))
        mati.modules.addAll(elements = listOf(eventModule))
        valen.modules.addAll(elements = listOf(eventModule, directiveModule))
        theo.modules.addAll(elements = listOf(eventModule))

        userRepo.saveAll(listOf(adrian, diego, pica, mati, valen, theo))
    }

    fun createEvents () {
        var adrian: Employee = userRepo.findByEmail(email = "adrian@mail.com")
        var diego: Employee = userRepo.findByEmail(email = "diego@mail.com")
        var pica: Employee = userRepo.findByEmail(email = "pica@mail.com")


        val event01:Event = eventAdrian(creatorEmployee = adrian, participantsEmployees = setOf(diego, pica))
        val event02:Event = eventDiego(creatorEmployee = diego, participantsEmployees = setOf(pica))
        val event03:Event = eventPica(creatorEmployee = pica, participantsEmployees = setOf())

        eventRepo.saveAll(listOf(event01, event02, event03))


    }


}
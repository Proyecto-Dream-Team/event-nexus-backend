package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.admin.AdminModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.repports.RepportModule
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
    @Autowired val eventRepo: EventRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        createAccounts()
        createUsers()
//        createModules()
        createEvents()
    }

    fun createAccounts() {
        val adrian: Authentication = credentialsAdrian()
        val diego: Authentication = credentialsDiego()
        val matias: Authentication = credentialsMatias()
        val pica: Authentication = credentialsPica()
        val valen: Authentication = credentialsValen()
        val theo: Authentication = credentialsTheo()
        val mock: Authentication = credentialsMock()
        val accounts: List<Authentication> = listOf(adrian, diego, matias, pica, valen, theo, mock)
        authRepo.saveAll(accounts)
    }

    fun createUsers () {

        var credential: Authentication? = authRepo.findByUsername(username = "adrian")
        val adrian = employeeAccountAdrian(credential!!)

        credential = authRepo.findByUsername(username = "diego")
        val diego = employeeAccountDiego(credential!!)

        credential = authRepo.findByUsername(username = "pica")
        val pica = employeeAccountPica(credential!!)

        credential = authRepo.findByUsername(username = "mati")
        val mati = employeeAccountMati(credential!!)

        credential = authRepo.findByUsername(username = "valen")
        val valen = employeeAccountValen(credential!!)

        credential = authRepo.findByUsername(username = "theo")
        val theo = employeeAccountTheo(credential!!)

        credential = authRepo.findByUsername(username = "mock")
        val mock = employeeAccountMockParaJugar(credential!!)

        val users : List<Employee> = listOf(adrian, diego, pica, mati, valen, theo, mock)
        userRepo.saveAll(users)
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
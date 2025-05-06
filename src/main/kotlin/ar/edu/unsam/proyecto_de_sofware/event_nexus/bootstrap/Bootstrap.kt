package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.EventRepository
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
        var mati: Employee = userRepo.findByEmail(email = "mati@mail.com")
        var valen: Employee = userRepo.findByEmail(email = "valen@mail.com")
        var theo: Employee = userRepo.findByEmail(email = "theo@mail.com")


        val event01:Event = event01(creatorEmployee = adrian, participantsEmployees = setOf(diego, pica))
        val event02:Event = event02(creatorEmployee = adrian, participantsEmployees = setOf(mati))
        val event03:Event = event03(creatorEmployee = adrian, participantsEmployees = setOf(valen))

        val event04:Event = event04(creatorEmployee = diego, participantsEmployees = setOf(pica, mati))
        val event05:Event = event05(creatorEmployee = diego, participantsEmployees = setOf(pica))
        val event06:Event = event06(creatorEmployee = diego, participantsEmployees = setOf(pica))

        val event07:Event = event07(creatorEmployee = pica, participantsEmployees = setOf(valen, mati))
        val event08:Event = event08(creatorEmployee = pica, participantsEmployees = setOf(valen))
        val event09:Event = event09(creatorEmployee = pica, participantsEmployees = setOf(valen))

        val event10:Event = event10(creatorEmployee = valen, participantsEmployees = setOf(mati, theo))
        val event11:Event = event11(creatorEmployee = valen, participantsEmployees = setOf(mati))
        val event12:Event = event12(creatorEmployee = valen, participantsEmployees = setOf(mati))

        val event13:Event = event13(creatorEmployee = mati, participantsEmployees = setOf(theo, adrian))
        val event14:Event = event14(creatorEmployee = mati, participantsEmployees = setOf(theo))
        val event15:Event = event15(creatorEmployee = mati, participantsEmployees = setOf(theo))

        val event16:Event = event16(creatorEmployee = theo, participantsEmployees = setOf(adrian, diego))
        val event17:Event = event17(creatorEmployee = theo, participantsEmployees = setOf(adrian))
        val event18:Event = event18(creatorEmployee = theo, participantsEmployees = setOf(adrian))

        eventRepo.saveAll(listOf(
            event01, event02, event03,
            event04, event05, event06,
            event07, event08, event09,
            event10, event11, event12,
            event13, event14, event15,
            event16, event17, event18
        ))
    }


}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectivePriority
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.DirectiveRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.EventRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class Bootstrap(
    @Autowired val authRepo: AuthRepository,
    @Autowired val userRepo: UserRepository,
    @Autowired val eventRepo: EventRepository,
    @Autowired val directiveRepo: DirectiveRepository,
    @Autowired val passwordEncoder: PasswordEncoder,
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        createUsers()
        createEvents()
        createDirectives()
    }

    fun createUsers () {
        val adrian = employeeAccountAdrian(credentialsAdrian(passwordEncoder.encode("adrian")))
        val diego = employeeAccountDiego(credentialsDiego(passwordEncoder.encode("diego")))
        val pica = employeeAccountPica(credentialsMatias(passwordEncoder.encode("pica")))
        val mati = employeeAccountMati(credentialsPica(passwordEncoder.encode("mati")))
        val valen = employeeAccountValen(credentialsValen(passwordEncoder.encode("valen")))
        val theo = employeeAccountTheo(credentialsTheo(passwordEncoder.encode("theo")))
        val users : List<Employee> = listOf(adrian, diego, pica, mati, valen, theo)
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

    fun createDirectives () {
        var adrian: Employee = userRepo.findByEmail(email = "adrian@mail.com")


        val directive01: Directive = directive01(creatorEmployee = adrian, directivePriority= DirectivePriority.HIGH)
        val directive02: Directive = directive02(creatorEmployee = adrian, directivePriority= DirectivePriority.MEDIUM)
        val directive03: Directive = directive03(creatorEmployee = adrian, directivePriority= DirectivePriority.LOW)

        directiveRepo.saveAll(listOf(
            directive01, directive02, directive03
        ))
    }


}
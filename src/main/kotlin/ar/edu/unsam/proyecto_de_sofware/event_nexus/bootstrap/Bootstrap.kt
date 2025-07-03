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
        val mati = employeeAccountMati(credentialsMatias(passwordEncoder.encode("mati")))
        val pica = employeeAccountPica(credentialsPica(passwordEncoder.encode("pica")))
        val valen = employeeAccountValen(credentialsValen(passwordEncoder.encode("valen")))
        val theo = employeeAccountTheo(credentialsTheo(passwordEncoder.encode("theo")))

        val luana = employeeAccountLuana(credentialsLuana(passwordEncoder.encode("luana")))
        val facundo = employeeAccountFacundo(credentialsFacundo(passwordEncoder.encode("facundo")))
        val sofia = employeeAccountSofia(credentialsSofia(passwordEncoder.encode("sofia")))
        val joaquin = employeeAccountJoaquin(credentialsJoaquin(passwordEncoder.encode("joaquin")))
        val emilia = employeeAccountEmilia(credentialsEmilia(passwordEncoder.encode("emilia")))
        val ignacio = employeeAccountIgnacio(credentialsIgnacio(passwordEncoder.encode("ignacio")))
        val camila = employeeAccountCamila(credentialsCamila(passwordEncoder.encode("camila")))
        val santiago = employeeAccountSantiago(credentialsSantiago(passwordEncoder.encode("santiago")))
        val agustina = employeeAccountAgustina(credentialsAgustina(passwordEncoder.encode("agustina")))
        val nicolas = employeeAccountNicolas(credentialsNicolas(passwordEncoder.encode("nicolas")))
        val micaela = employeeAccountMicaela(credentialsMicaela(passwordEncoder.encode("micaela")))
        val gaston = employeeAccountGaston(credentialsGaston(passwordEncoder.encode("gaston")))
        val florencia = employeeAccountFlorencia(credentialsFlorencia(passwordEncoder.encode("florencia")))
        val lucas = employeeAccountLucas(credentialsLucas(passwordEncoder.encode("lucas")))
        val victoria = employeeAccountVictoria(credentialsVictoria(passwordEncoder.encode("victoria")))

        val users : List<Employee> = listOf(adrian, diego, pica, mati, valen, theo,
            luana, facundo, sofia, joaquin, emilia, ignacio, camila, santiago, agustina,
            nicolas, micaela, gaston, florencia,lucas, victoria)

        userRepo.saveAll(users)
    }

    fun createEvents () {
        // Cargar todos los usuarios existentes y nuevos al inicio
        val adrian: Employee = userRepo.findByEmail(email = "adrian@mail.com")
        val diego: Employee = userRepo.findByEmail(email = "diego@mail.com")
        val pica: Employee = userRepo.findByEmail(email = "pica@mail.com")
        val mati: Employee = userRepo.findByEmail(email = "mati@mail.com")
        val valen: Employee = userRepo.findByEmail(email = "valen@mail.com")
        val theo: Employee = userRepo.findByEmail(email = "theo@mail.com")

        // Cargar los 15 usuarios nuevos
        val luana: Employee = userRepo.findByEmail(email = "luana@mail.com")
        val facundo: Employee = userRepo.findByEmail(email = "facundo@mail.com")
        val sofia: Employee = userRepo.findByEmail(email = "sofia@mail.com")
        val joaquin: Employee = userRepo.findByEmail(email = "joaquin@mail.com")
        val emilia: Employee = userRepo.findByEmail(email = "emilia@mail.com")
        val ignacio: Employee = userRepo.findByEmail(email = "ignacio@mail.com")
        val camila: Employee = userRepo.findByEmail(email = "camila@mail.com")
        val santiago: Employee = userRepo.findByEmail(email = "santiago@mail.com")
        val agustina: Employee = userRepo.findByEmail(email = "agustina@mail.com")
        val nicolas: Employee = userRepo.findByEmail(email = "nicolas@mail.com")
        val micaela: Employee = userRepo.findByEmail(email = "micaela@mail.com")
        val gaston: Employee = userRepo.findByEmail(email = "gaston@mail.com")
        val florencia: Employee = userRepo.findByEmail(email = "florencia@mail.com")
        val lucas: Employee = userRepo.findByEmail(email = "lucas@mail.com")
        val victoria: Employee = userRepo.findByEmail(email = "victoria@mail.com")


        // --- Eventos Existentes (repartiendo los nuevos usuarios) ---
        val event01: Event = event01(creatorEmployee = adrian, participantsEmployees = setOf(diego, pica, luana))
        val event02: Event = event02(creatorEmployee = adrian, participantsEmployees = setOf(mati, facundo))
        val event03: Event = event03(creatorEmployee = adrian, participantsEmployees = setOf(valen, sofia))

        val event04: Event = event04(creatorEmployee = diego, participantsEmployees = setOf(pica, mati, joaquin))
        val event05: Event = event05(creatorEmployee = diego, participantsEmployees = setOf(pica, emilia))
        val event06: Event = event06(creatorEmployee = diego, participantsEmployees = setOf(pica, ignacio))

        val event07: Event = event07(creatorEmployee = pica, participantsEmployees = setOf(valen, mati, camila))
        val event08: Event = event08(creatorEmployee = pica, participantsEmployees = setOf(valen, santiago))
        val event09: Event = event09(creatorEmployee = pica, participantsEmployees = setOf(valen, agustina))

        val event10: Event = event10(creatorEmployee = valen, participantsEmployees = setOf(mati, theo, nicolas))
        val event11: Event = event11(creatorEmployee = valen, participantsEmployees = setOf(mati, micaela))
        val event12: Event = event12(creatorEmployee = valen, participantsEmployees = setOf(mati, gaston))

        val event13: Event = event13(creatorEmployee = mati, participantsEmployees = setOf(theo, adrian, florencia))
        val event14: Event = event14(creatorEmployee = mati, participantsEmployees = setOf(theo, lucas))
        val event15: Event = event15(creatorEmployee = mati, participantsEmployees = setOf(theo, victoria))

        val event16: Event = event16(creatorEmployee = theo, participantsEmployees = setOf(adrian, diego))
        val event17: Event = event17(creatorEmployee = theo, participantsEmployees = setOf(adrian))
        val event18: Event = event18(creatorEmployee = theo, participantsEmployees = setOf(adrian))

        // --- INICIO: 10 Eventos Nuevos (con nuevos usuarios como creadores y participantes) ---
        val event19: Event = event19(creatorEmployee = luana, participantsEmployees = setOf(facundo, sofia))
        val event20: Event = event20(creatorEmployee = facundo, participantsEmployees = setOf(joaquin, emilia))
        val event21: Event = event21(creatorEmployee = sofia, participantsEmployees = setOf(ignacio, camila))

        val event22: Event = event22(creatorEmployee = joaquin, participantsEmployees = setOf(santiago, agustina))
        val event23: Event = event23(creatorEmployee = emilia, participantsEmployees = setOf(nicolas, micaela))
        val event24: Event = event24(creatorEmployee = ignacio, participantsEmployees = setOf(gaston, florencia))

        val event25: Event = event25(creatorEmployee = camila, participantsEmployees = setOf(lucas, victoria))
        val event26: Event = event26(creatorEmployee = santiago, participantsEmployees = setOf(luana, theo))
        val event27: Event = event27(creatorEmployee = agustina, participantsEmployees = setOf(adrian, diego))
        val event28: Event = event28(creatorEmployee = nicolas, participantsEmployees = setOf(mati, valen))
        // --- FIN: 10 Eventos Nuevos ---

        eventRepo.saveAll(listOf(
            event01, event02, event03,
            event04, event05, event06,
            event07, event08, event09,
            event10, event11, event12,
            event13, event14, event15,
            event16, event17, event18,
            event19, event20, event21,
            event22, event23, event24,
            event25, event26, event27,
            event28
        ))
    }

    fun createDirectives () {
        var adrian: Employee = userRepo.findByEmail(email = "adrian@mail.com")
        var diego: Employee = userRepo.findByEmail(email = "diego@mail.com")
        var theo: Employee = userRepo.findByEmail(email = "theo@mail.com")

        val directive01: Directive = directive01(creatorEmployee = adrian, directivePriority= DirectivePriority.HIGH)
        val directive02: Directive = directive02(creatorEmployee = adrian, directivePriority= DirectivePriority.MEDIUM)
        val directive03: Directive = directive03(creatorEmployee = adrian, directivePriority= DirectivePriority.LOW)

        val directive04: Directive = directive04(creatorEmployee = adrian, directivePriority= DirectivePriority.HIGH)
        val directive05: Directive = directive05(creatorEmployee = diego, directivePriority= DirectivePriority.HIGH)
        val directive06: Directive = directive06(creatorEmployee = diego, directivePriority= DirectivePriority.HIGH)

        val directive07: Directive = directive07(creatorEmployee = diego, directivePriority= DirectivePriority.MEDIUM)
        val directive08: Directive = directive08(creatorEmployee = diego, directivePriority= DirectivePriority.LOW)
        val directive09: Directive = directive09(creatorEmployee = diego, directivePriority= DirectivePriority.LOW)

        val directive10: Directive = directive10(creatorEmployee = theo, directivePriority = DirectivePriority.HIGH)
        val directive11: Directive = directive11(creatorEmployee = theo, directivePriority = DirectivePriority.MEDIUM)
        val directive12: Directive = directive12(creatorEmployee = theo, directivePriority = DirectivePriority.LOW)

        val directive13: Directive = directive13(creatorEmployee = theo, directivePriority = DirectivePriority.HIGH)
        val directive14: Directive = directive14(creatorEmployee = adrian, directivePriority = DirectivePriority.MEDIUM) // Un existente
        val directive15: Directive = directive15(creatorEmployee = diego, directivePriority = DirectivePriority.LOW)    // Un existente
        // --- FIN: 6 Directivas Adicionales ---

        directiveRepo.saveAll(listOf(
            directive01, directive02, directive03,
            directive04, directive05, directive06,
            directive07, directive08, directive09,
            directive10, directive11, directive12, // Nuevas directivas
            directive13, directive14, directive15  // Nuevas directivas
        ))
    }


}
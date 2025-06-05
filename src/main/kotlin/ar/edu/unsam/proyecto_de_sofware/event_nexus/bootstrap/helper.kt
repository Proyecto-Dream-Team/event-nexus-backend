package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventType
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// AUTH CREDENTIALS
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun credentialsAdrian(): Authentication{
    return Authentication().apply {
        username = "adrian"
        password = "adrian"
        //email = "adrian@mail.com"
        role = Role.ADMIN
    }
}
fun credentialsDiego(): Authentication{
    return Authentication().apply {
        username = "diego"
        password = "diego"
        //email = "diego@mail.com"
        role = Role.SUPERVISOR
    }
}
fun credentialsMatias(): Authentication{
    return Authentication().apply {
        username = "mati"
        password = "mati"
        //email = "mati@mail.com"
        role = Role.DEV
    }
}
fun credentialsPica(): Authentication{
    return Authentication().apply {
        username = "pica"
        password = "pica"
        //email = "pica@mail.com"
        role = Role.HR
    }
}
fun credentialsValen(): Authentication{
    return Authentication().apply {
        username = "valen"
        password = "valen"
//        email = "valen@mail.com"
        role = Role.DEV
    }
}

fun credentialsTheo(): Authentication{
    return Authentication().apply {
        username = "theo"
        password = "theo"
//        email = "theo@mail.com"
        role = Role.SUPERVISOR
    }
}

fun credentialsMock(): Authentication{
    return Authentication().apply {
        username = "mock"
        password = "mock"
//        email = "mock@mail.com"
        role = Role.ADMIN
    }
}

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EMPLOYEE ACCOUNTS
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun employeeAccountAdrian(credential: Authentication): Admin{
    return Admin().apply {
        name = "Adrian"
        lastname = "Perez"
        job = credential.role.name
        phone = "12341234"
        email = "adrian@mail.com"
        address = "calle posta 123"
        credentials = credential
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountDiego(credential: Authentication): Employee{
    return Employee().apply {
        name = "Diego"
        lastname = "Lentz"
        job = credential.role.name
        phone = "12341234"
        email = "diego@mail.com"
        address = "calle falsa 123"
        credentials = credential
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountPica(credential: Authentication): Employee{
    return Employee().apply {
        name = "Pedro"
        lastname = "McGeraghty"
        job = credential.role.name
        phone = "12341234"
        email = "pica@mail.com"
        address = "Tambien calle falsa 123"
        credentials = credential
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountMati(credential: Authentication): Employee{
    return Employee().apply {
        name = "Matias"
        lastname = "Diaz"
        job = credential.role.name
        phone = "12341234"
        email = "mati@mail.com"
        address = "Tambien calle falsa 123"
        credentials = credential
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}

fun employeeAccountValen(credential: Authentication): Employee{
    return Employee().apply {
        name = "Valen"
        lastname = "Pugliese"
        job = credential.role.name
        phone = "12341234"
        email = "valen@mail.com"
        address = "Tambien calle falsa 123"
        credentials = credential
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountTheo(credential: Authentication): Employee{
    return Employee().apply {
        name = "Theo"
        lastname = "Narmontas"
        job = credential.role.name
        phone = "12341234"
        email = "theo@mail.com"
        address = "Tambien calle falsa 123"
        credentials = credential
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountMockParaJugar(credential: Authentication): Employee{
    return Employee().apply {
        name = "MOck"
        lastname = "MOck Mock"
        job = credential.role.name
        phone = "12341234"
        email = "mock@mail.com"
        address = "Calle mock"
        credentials = credential
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EVENTS
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

fun event01(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Adrian"
        description = "Juntada de estudio. Copate"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = false
        type = EventType.CAPACITACION
    }
}

fun event02(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Adrian"
        description = "Fulbito salvaje"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.DEPORTIVO
    }
}

fun event03(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Adrian"
        description = "Free fire con los pibes"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.SOCIAL
    }
}

fun event04(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Diego"
        description = "Asado en casa"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = false
        type = EventType.SOCIAL
    }
}

fun event05(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Diego"
        description = "Fulbito salvaje. Revancha contra los profes"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.DEPORTIVO
    }
}

fun event06(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Diego"
        description = "Trote picante"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.DEPORTIVO
    }
}

fun event07(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Pica"
        description = "Salida de Skate. A la tarde"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = false
        type = EventType.DEPORTIVO
    }
}

fun event08(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Pica"
        description = "Salida de Skate. A la noche"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.DEPORTIVO
    }
}

fun event09(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Pica"
        description = "Salida de Skate. Forever"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.DEPORTIVO
    }
}

fun event10(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Valen"
        description = "Proyecto .NET. Solo devs"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = false
        type = EventType.CAPACITACION
    }
}

fun event11(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Valen"
        description = "Juntada de estudio. Haskell"
        expirationDate = LocalDateTime.now()
            .plus(1, ChronoUnit.DAYS)
            .plus(4, ChronoUnit.HOURS)
            .plus(27, ChronoUnit.MINUTES)

        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.EQUIPO
    }
}

fun event12(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Valen"
        description = "Lore ipsum"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.EJECUTIVO
    }
}

fun event13(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Mati"
        description = "Curso de Spring Security"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = false
        type = EventType.CAPACITACION
    }
}

fun event14(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Mati"
        description = "Capacitacion Spring Boot REST"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.CAPACITACION
    }
}

fun event15(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Mati"
        description = "Age of Empires 3. Solo valientes"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.SOCIAL
    }
}

fun event16(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Theo"
        description = "Salida a bosques de Palermo"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = false
        type = EventType.EQUIPO
    }
}

fun event17(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Theo"
        description = "Juntada de estudio. PHM"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.EQUIPO
    }
}


fun event18(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Theo"
        description = "Lore ipsum"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.EJECUTIVO
    }
}

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Directives
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        email = "adrian@mail.com"
        role = Role.ADMIN
    }
}
fun credentialsDiego(): Authentication{
    return Authentication().apply {
        username = "diego"
        password = "diego"
        email = "diego@mail.com"
        role = Role.SUPERVISOR
    }
}
fun credentialsMatias(): Authentication{
    return Authentication().apply {
        username = "mati"
        password = "mati"
        email = "mati@mail.com"
        role = Role.DEV
    }
}
fun credentialsPica(): Authentication{
    return Authentication().apply {
        username = "pica"
        password = "pica"
        email = "pica@mail.com"
        role = Role.HR
    }
}
fun credentialsValen(): Authentication{
    return Authentication().apply {
        username = "valen"
        password = "valen"
        email = "valen@mail.com"
        role = Role.DEV
    }
}

fun credentialsTheo(): Authentication{
    return Authentication().apply {
        username = "theo"
        password = "theo"
        email = "theo@mail.com"
        role = Role.SUPERVISOR
    }
}

fun credentialsMock(): Authentication{
    return Authentication().apply {
        username = "mock"
        password = "mock"
        email = "mock@mail.com"
        role = Role.MOCK_PARA_JUGAR
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
        email = credential.email
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
        email = credential.email
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
        email = credential.email
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
        email = credential.email
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
        email = credential.email
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
        email = credential.email
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
        email = credential.email
        address = "Calle mock"
        credentials = credential
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EVENTS
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

fun eventAdrian(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Adrian"
        description = "Juntada de estudio. Copate"
        date = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
        type = EventType.CAPACITACION
    }
}

fun eventDiego(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Diego"
        description = "Fulbito salvaje"
        date = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        type = EventType.DEPORTIVO
    }
}

fun eventPica(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        title = "Evento de Pica"
        description = "Free fire con los pibes"
        date = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
    }
}
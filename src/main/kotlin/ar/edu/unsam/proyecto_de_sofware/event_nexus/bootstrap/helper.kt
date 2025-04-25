package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
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
        role = Role.EMPLOYEE_FULL
    }
}
fun credentialsMatias(): Authentication{
    return Authentication().apply {
        username = "matias"
        password = "matias"
        email = "matias@mail.com"
        role = Role.ADMIN
    }
}
fun credentialsPica(): Authentication{
    return Authentication().apply {
        username = "pica"
        password = "pica"
        email = "pica@mail.com"
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
        phone = "12341234"
        email = credential.email
        address = "calle posta 123"
        credentials = credential
    }
}
fun employeeAccountDiego(credential: Authentication): Employee{
    return Employee().apply {
        name = "Diego"
        lastname = "Lentz"
        phone = "12341234"
        email = credential.email
        address = "calle falsa 123"
        credentials = credential
    }
}
fun employeeAccountPica(credential: Authentication): Employee{
    return Employee().apply {
        name = "Pedro"
        lastname = "McGeraghty"
        phone = "12341234"
        email = credential.email
        address = "Tambien calle falsa 123"
        credentials = credential
    }
}

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EVENTS
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

fun eventAdrian(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        name = "Evento de Adrian"
        date = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableList()
    }
}

fun eventDiego(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        name = "Evento de Diego"
        date = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableList()
    }
}

fun eventPica(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event{
    return Event().apply {
        name = "Evento de Pica"
        date = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        creator = creatorEmployee
        participants = participantsEmployees.toMutableList()
    }
}
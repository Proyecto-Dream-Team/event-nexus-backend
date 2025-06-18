package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventType
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// AUTH CREDENTIALS
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun credentialsAdrian(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "adrian"
        password = encodedPassword
        role = Role.ADMIN
    }
}
fun credentialsDiego(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "diego"
        password = encodedPassword
        role = Role.SUPERVISOR
    }
}
fun credentialsMatias(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "mati"
        password = encodedPassword
        role = Role.DEV
    }
}
fun credentialsPica(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "pica"
        password = encodedPassword
        role = Role.HR
    }
}
fun credentialsValen(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "valen"
        password = encodedPassword
        role = Role.DEV
    }
}

fun credentialsTheo(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "theo"
        password = encodedPassword
        role = Role.SUPERVISOR
    }
}


// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EMPLOYEE ACCOUNTS
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun employeeAccountAdrian(credential: Credentials): Admin{
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
fun employeeAccountDiego(credential: Credentials): Employee{
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
fun employeeAccountPica(credential: Credentials): Employee{
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
fun employeeAccountMati(credential: Credentials): Employee{
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

fun employeeAccountValen(credential: Credentials): Employee{
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
fun employeeAccountTheo(credential: Credentials): Employee{
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



// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Directives
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

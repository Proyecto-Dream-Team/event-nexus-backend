package ar.edu.unsam.proyecto_de_sofware.event_nexus

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventType
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class AuthRepositoryUtil {
    fun singleAccount(): Credentials {
        return Credentials().apply {
            username = "adrian"
            password = "adrian"
            role = Role.ADMIN
        }
    }

    fun multipleAccounts(): List<Credentials> {
        return listOf(
            Credentials().apply {
                username = "adrian1"
                password = "adrian1"
                role = Role.ADMIN
            },
            Credentials().apply {
                username = "adrian2"
                password = "adrian2"
                role = Role.ADMIN
            }
        )
    }

}

class EmployeeRepoUtil {
    fun single(identifier:Int): Employee {
        return Employee().apply {
            name = ""
            lastname = ""
            job = Role.ADMIN.name
            address = "addres"
            phone = "12312"
            email = "mail$identifier@mail"
            permissions = mutableSetOf(Permission.CREAR_EVENTO_EQUIPO, Permission.CREAR_EVENTO_DEPORTIVO)
            credentials = Credentials().apply {
                email = "mail$identifier@mail"
                username = "back$identifier"
                password = "back$identifier"
                role = Role.ADMIN
            }
        }

        fun multiple(): List<Employee> {
            return listOf(
                Employee().apply {
                    name = ""
                    lastname = ""
                    job = Role.ADMIN.name
                    address = "addres"
                    phone = "12312"
                    email = "mail@mail"
                    permissions = mutableSetOf(Permission.CREAR_EVENTO_EQUIPO, Permission.CREAR_EVENTO_DEPORTIVO)
                    credentials = Credentials().apply {
                        email = "mail1@mail"
                        username = "back1"
                        password = "back1"
                        role = Role.ADMIN
                    }
                },
                Employee().apply {
                    name = ""
                    lastname = ""
                    job = Role.ADMIN.name
                    address = "addres"
                    phone = "12312"
                    email = "mail@mail"
                    permissions = mutableSetOf(Permission.CREAR_EVENTO_EQUIPO, Permission.CREAR_EVENTO_DEPORTIVO)
                    credentials = Credentials().apply {
                        email = "mail2@mail"
                        username = "back2"
                        password = "back2"
                        role = Role.ADMIN
                    }
                }
            )
        }
    }
}

class EventRepoUtil {
    fun single(creatorEmployee:Employee, participantsEmployee: MutableSet<Employee>?): Event {
        return Event().apply {
            creator = creatorEmployee
            participants = participantsEmployee ?: mutableSetOf()
            title = ""
            description = ""
            expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
            type = EventType.DEPORTIVO
        }

        fun multiple(creatorEmployee:Employee): List<Event> {
            return listOf(
                Event().apply {
                    creator = creatorEmployee
                    participants = mutableSetOf()
                    title = "AAAAA"
                    description = "BBBBB"
                    expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
                    type = EventType.DEPORTIVO
                },
                Event().apply {
                    creator = creatorEmployee
                    participants = mutableSetOf()
                    title = "ASDASD"
                    description = "ASDASDAS"
                    expirationDate = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
                    type = EventType.SOCIAL
                }
            )
        }
    }
}
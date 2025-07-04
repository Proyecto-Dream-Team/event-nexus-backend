package ar.edu.unsam.proyecto_de_sofware.event_nexus.integration.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.EmployeeRepoUtil
import ar.edu.unsam.proyecto_de_sofware.event_nexus.EventRepoUtil
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventType
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.EventRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import io.kotest.core.spec.style.AnnotationSpec.BeforeEach
import io.kotest.core.spec.style.AnnotationSpec.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.test.assertEquals

@DataJpaTest
class EventRepositoryTest {

    @Autowired
    lateinit var eventRepository: EventRepository


    @Autowired
    lateinit var employeeRepository: UserRepository

    val employeeUtil = EmployeeRepoUtil()
    val eventUtil = EventRepoUtil()

    @AfterEach
    fun clear(){
        employeeRepository.deleteAll()
        eventRepository.deleteAll()
    }

    fun fixture(){
        employeeRepository.save(employeeUtil.single(1))
        val empleado = employeeRepository.findByEmail("mail1@mail")
        eventRepository.save(eventUtil.single(empleado, null))
    }
    fun fixture2(){
        employeeRepository.saveAll(listOf(
            employeeUtil.single(1),
            employeeUtil.single(2),
            employeeUtil.single(3)
        ))
        val empleado1 = employeeRepository.findByEmail("mail1@mail")
        val empleado2 = employeeRepository.findByEmail("mail2@mail")
        val empleado3 = employeeRepository.findByEmail("mail3@mail")
        eventRepository.save(eventUtil.single(empleado1, mutableSetOf(empleado2, empleado3)))
    }
    @Test
    fun findByCreator() {
        fixture()
        val empleado = employeeRepository.findByEmail("mail1@mail")
        val evento = eventRepository.findByCreator(empleado)
        assertEquals(expected = empleado.id, actual = evento[0].creator.id)
    }

    @Test
    fun findByParticipant() {
        fixture2()
        val empleadoCreador = employeeRepository.findByEmail("mail1@mail")
        val empleado = employeeRepository.findByEmail("mail2@mail")
        val evento = eventRepository.findByParticipants(empleado)
        assertEquals(expected = empleadoCreador.id, actual = evento[0].creator.id)
    }




}
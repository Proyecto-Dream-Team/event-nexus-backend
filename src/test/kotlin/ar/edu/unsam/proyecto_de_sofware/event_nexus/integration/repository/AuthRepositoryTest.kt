package ar.edu.unsam.proyecto_de_sofware.event_nexus.integration.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.AuthRepositoryUtil
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.assertEquals

@DataJpaTest
class AuthRepositoryTest {

    @Autowired
    lateinit var authRepository: AuthRepository

    val authRepoUtil = AuthRepositoryUtil()

    @Test
    fun findByUsernameFailed() {
        val account: Credentials? = authRepository.findByUsername(username = "invalid_username")
        assertEquals(expected = null, actual = account)
    }

    @Test
    fun findByUsernameSuccess() {
        authRepository.save(authRepoUtil.singleAccount())
        val account: Credentials? = authRepository.findByUsername(username = "adrian")
        assertEquals(expected = "adrian", actual = account!!.username)
        assertEquals(expected = Role.ADMIN, actual = account!!.role)
    }


    @Test
    fun findAll() {
        authRepository.saveAll(authRepoUtil.multipleAccounts())
        val accounts: List<Credentials> = authRepository.findAll().toList()
        assertEquals(expected = 2, actual = accounts.size)
    }


}
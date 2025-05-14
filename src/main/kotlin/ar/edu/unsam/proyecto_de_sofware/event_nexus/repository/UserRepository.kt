package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Authentication
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import jakarta.persistence.Entity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface UserRepository: CrudRepository<Employee, Long> {

    fun findByEmail(email: String): Employee

    fun findByCredentials_Id(id: Long): Employee

    @Query("""
        SELECT e.credentials FROM Employee e
        WHERE e.email = :email
    """)
    fun findAuthenticationByEmail(@Param("email") email: String): Authentication?

}
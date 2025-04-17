package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import jakarta.persistence.Entity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository: CrudRepository<Employee, Long> {

    @Query(nativeQuery = true, value = """
        SELECT * FROM employee e
        WHERE e.dtype = :type
    """)
    fun findWithType(type: String = ""): List<Employee>

    @EntityGraph(attributePaths = ["permissions"])
    override fun findById(id: Long): Optional<Employee>

    fun getByCredentials_Id(id: Long): Employee
}
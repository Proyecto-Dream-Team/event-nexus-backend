package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<Employee, Long> {

    @Query(nativeQuery = true, value = """
        SELECT * FROM employee e
        WHERE e.dtype = :type
    """)
    fun findWithType(type: String = ""): List<Employee>
}
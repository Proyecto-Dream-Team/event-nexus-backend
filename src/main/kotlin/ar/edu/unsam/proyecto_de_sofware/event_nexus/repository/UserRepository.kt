package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository: CrudRepository<Employee, Long> {

    fun findByEmail(email: String): Employee

    fun findByCredentials_Id(id: Long): Employee

    @Query("""
        SELECT e.credentials FROM Employee e
        WHERE e.email = :email
    """)
    fun findAuthenticationByEmail(@Param("email") email: String): Credentials?

    @Query("""
        SELECT e FROM Employee e
        WHERE LOWER(e.lastname) LIKE  %:search% OR LOWER(e.name) LIKE  %:search%
    """)
    fun findBySearch(@Param("search") search: String): List<Employee>


}
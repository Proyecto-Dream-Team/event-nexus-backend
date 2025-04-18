package ar.edu.unsam.proyecto_de_sofware.event_nexus.repository

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.DirectiveModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface AppModuleRepository: CrudRepository<AppModule, Long>  {

    //SOLO USADO PARA BOOTSTRAP
    @Query(nativeQuery = true, value = """
        SELECT * FROM app_module appm
        WHERE appm.dtype = :type
    """)
    fun findDirectiveModule(type: String = DirectiveModule::class.simpleName!!): DirectiveModule

    //SOLO USADO PARA BOOTSTRAP
    @Query(nativeQuery = true, value = """
        SELECT * FROM app_module appm
        WHERE appm.dtype = :type
    """)
    fun findEventModule(type: String = EventModule::class.simpleName!!): EventModule

}
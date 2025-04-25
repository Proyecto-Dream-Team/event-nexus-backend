package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.ModuleRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ModuleService(
    val userRepository: UserRepository,
    val moduleRepository: ModuleRepository
) {

    fun modulosAll():List<AppModule>{
        return moduleRepository.findAll().toList()
    }

    fun employeeModulosById(employeeId:Long):List<AppModule>{
        val employee:Employee = userRepository.findById(employeeId).get()
        return employee.modules.toList()
    }
}
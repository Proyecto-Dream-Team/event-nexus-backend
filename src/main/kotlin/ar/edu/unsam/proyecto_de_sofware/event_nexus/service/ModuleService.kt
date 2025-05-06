package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.ModuleRepository
import org.springframework.stereotype.Service

@Service
class ModuleService() {

    val moduleRepository: ModuleRepository = ModuleRepository()

    fun all():List<AppModule>{
        return moduleRepository.findAll()
    }
}
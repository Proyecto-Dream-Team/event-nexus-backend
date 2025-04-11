package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ModuleService(
    @Autowired val userService: UserService
) {
    fun getAll(id: Int): List<AppModule> {
        val user = userService.getUser(id)
        return user.modules
    }
}
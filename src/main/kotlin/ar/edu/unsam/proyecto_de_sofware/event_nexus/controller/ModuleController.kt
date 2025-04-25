package ar.edu.unsam.proyecto_de_sofware.event_nexus.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.AppModule
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.ModuleService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
@RequestMapping()
class ModuleController(val moduleService: ModuleService) {

    @GetMapping("/module")
    fun modules():List<AppModule>{
        return moduleService.modulosAll()
    }


    @GetMapping("/module/{id}")
    fun employeeModules(@PathVariable id: Long):List<AppModule>{
        return moduleService.employeeModulosById(id)
    }

}
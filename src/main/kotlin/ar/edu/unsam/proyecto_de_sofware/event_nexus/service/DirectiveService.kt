package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.EntityPersistanceException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.directive.Directive
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.DirectiveRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class DirectiveService(
    val directiveRepository: DirectiveRepository
) {


    fun getAll():List<Directive>{
        return this.directiveRepository.findAll().toList()
    }

    @Transactional
    fun create(directive: Directive):Directive{
        try {
            return this.directiveRepository.save(directive)
        }catch (e:Exception){
            throw EntityPersistanceException(entityClassName = Directive::class.simpleName!!)
        }
    }
}
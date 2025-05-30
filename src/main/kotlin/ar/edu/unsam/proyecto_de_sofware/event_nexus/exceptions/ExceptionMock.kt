package ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST) //TODO aca no tendria que ser un NOT_FOUND
class BusinessException(override val message:String): Exception(message)

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(override val message:String): Exception(message)

@ResponseStatus(HttpStatus.NOT_MODIFIED)
class DataBaseNotModifiedException(override val message:String): RuntimeException(message)

@ResponseStatus(HttpStatus.FORBIDDEN)
class CommandNotAllowedException(commandName:String): Exception("Permiso <$commandName> denegado")


val existintEventTitleExceptionMessage:String = "Ya existe un evento con ese titulo. Prueba uno diferente"
@ResponseStatus(HttpStatus.CONFLICT) //TODO aca no tendria que ser un NOT_FOUND
class ExistintEventTitleException(override val message:String = existintEventTitleExceptionMessage): Exception(message)



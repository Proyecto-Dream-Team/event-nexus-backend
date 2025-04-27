package ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND) //TODO aca no tendria que ser un NOT_FOUND
class BusinessException(override val message:String): Exception(message)

@ResponseStatus(HttpStatus.NOT_MODIFIED)
class DataBaseNotModifiedException(override val message:String): RuntimeException(message)

@ResponseStatus(HttpStatus.FORBIDDEN)
class CommandNotAllowedException(commandName:String): Exception("Command <$commandName> not allowed")

@ResponseStatus(HttpStatus.FORBIDDEN)
class ModuleNotAllowedException(moduleName:String): Exception("Module <$moduleName> not allowed")


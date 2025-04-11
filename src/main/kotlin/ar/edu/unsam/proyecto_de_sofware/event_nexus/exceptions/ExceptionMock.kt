package ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class BusinessException(override val message:String): Exception(message)

@ResponseStatus(HttpStatus.FORBIDDEN)
class CommandNotAllowedException(commandName:String): Exception("Command <$commandName> not allowed")
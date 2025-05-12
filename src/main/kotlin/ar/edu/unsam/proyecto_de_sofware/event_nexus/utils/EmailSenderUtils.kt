package ar.edu.unsam.proyecto_de_sofware.event_nexus.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean

class EmailSenderUtils {
    fun subjectCreationUser() = "Creación de cuenta Event-Nexus"
    fun contentCreationUser(name: String) = """Buenas! Te informamos que tu cuenta de event-nexus ha sido creada exitosamente! Haz click en el siguiente enlace para para establecer tu contraseña y empezar a utilizar la app
        <aca iria el enlace>
        Bienvenido a bordo 🚀 !
            """.trimMargin()
    fun subjectEditProfile() = "Modificación de perfil Event-Nexus"

    fun contentEditProfile(name: String) = """Te informamos que la información de tu perfil ha sido actualizada correctamente por un administrador.
        Podrás verla reflejada en la sección de Mi perfil""".trimMargin()
}
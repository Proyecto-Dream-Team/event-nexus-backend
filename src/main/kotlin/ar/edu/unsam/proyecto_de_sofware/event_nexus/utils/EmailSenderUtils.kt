package ar.edu.unsam.proyecto_de_sofware.event_nexus.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean

class EmailSenderUtils {
    fun subjectCreationUser() = "Tu cuenta ha sido creada"
    fun contentCreationUser(name: String) = """Hola ${name}, tu cuenta ha sido modificado por un administrador.
        |Haz click en el siguiente link para establecer tus credenciales < https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlzKggZEy5E7c0UrXPxyf2FHukAZ6pkRa2iA&s >
        |Cualquier inconveniente contacta al administrador.
            """.trimMargin()
    fun subjectEditProfile() = "Tu perfil ha sido modificado"

    fun contentEditProfile(name: String) = """Hola ${name}, tu perfil ha sido modificado por un administrador.
            """.trimMargin()
}
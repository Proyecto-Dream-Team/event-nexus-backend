package ar.edu.unsam.proyecto_de_sofware.event_nexus.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean

class EmailSenderUtils {
    fun subjectCreationUser() = "Creación de cuenta Event-Nexus"
    fun contentCreationUser(name: String) = """
        <html>
        <body>
            <p>Hola <strong>$name</strong>,</p>
            <p>¡Te informamos que tu cuenta en <strong>Event-Nexus</strong> ha sido creada exitosamente!</p>
            <p>Haz clic en el siguiente enlace para establecer tu contraseña y empezar a utilizar la aplicación:</p>
            <p><a href="http://localhost:5173/register"> Establecer credenciales</a></p>
            <br/>
            <p>¡Bienvenido a bordo 🚀!</p>
            <hr/>
            <p style="font-size:12px;color:gray;">Este es un mensaje automático. Por favor, no respondas a este correo.</p>
        </body>
        </html>
    """.trimMargin()
    fun subjectEditProfile() = "Modificación de perfil Event-Nexus"

    fun contentEditProfile(name: String) = """
        <html>
        <body>
            <p>Hola <strong>$name</strong>,</p>
            <p>Te informamos que la información de tu perfil ha sido <strong>actualizada correctamente</strong> por un administrador.</p>
            <p>Puedes verla reflejada en la sección de <em>Mi perfil</em>.</p>
            <br/>
            <p>Saludos,</p>
            <p>Equipo Event-Nexus</p>
        </body>
        </html>
    """.trimMargin()
}
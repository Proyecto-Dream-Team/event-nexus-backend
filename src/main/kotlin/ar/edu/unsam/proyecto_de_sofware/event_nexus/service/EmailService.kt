package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.BusinessException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(private val mailSender: JavaMailSender) {
    fun sendEmail(to: String, subject: String, content: String) {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)

        try{
            helper.setTo(to)
            helper.setSubject(subject)
            helper.setText(content, true) // true si es HTML
            for (intento in 1..3) {
                try {
                    mailSender.send(message)
                    return
                } catch (e: Exception) {
                    if (intento == 3) throw BusinessException("Error al enviar el correo: ${e.message}")
                    Thread.sleep(1000)
                }
            }
        }catch (e: Exception){
            throw BusinessException("Falla en el envio del mail")
        }
    }
}
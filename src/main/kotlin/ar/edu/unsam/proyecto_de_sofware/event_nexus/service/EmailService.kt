package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(private val mailSender: JavaMailSender) {
    fun sendEmail(to: String, subject: String, content: String) {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)

        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(content, true) // true si es HTML

        try{
            mailSender.send(message)
        }catch (e: Exception){
            throw Exception("Falla en el envio del mail")
        }
    }
}
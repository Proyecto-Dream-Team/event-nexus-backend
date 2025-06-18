package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.NotificationRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository,
    private val userRepository: UserRepository,
) {

    fun getNotificationsByCreator(employeeId:Long): List<Notification> {
        val employee:Employee = this.userRepository.findById(employeeId).get()
        return notificationRepository.findNotificationsByListeners(listener =employee)
    }


    @Transactional
    fun save(notification: Notification):Notification{
        try {
            return this.notificationRepository.save(notification)
        }catch (e: Exception){
            throw e
        }
    }


}
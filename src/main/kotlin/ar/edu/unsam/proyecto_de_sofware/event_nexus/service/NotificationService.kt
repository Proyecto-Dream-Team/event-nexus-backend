package ar.edu.unsam.proyecto_de_sofware.event_nexus.service

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.NotificationRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository
) {

    fun getNotificationsByCreator(employeeId:Long): List<Notification> {
        return notificationRepository.findByCreator_Id(creatorId =employeeId)
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
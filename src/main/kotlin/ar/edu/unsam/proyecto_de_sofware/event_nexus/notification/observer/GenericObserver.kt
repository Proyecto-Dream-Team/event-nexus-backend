package ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.observer

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notifiable
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Notification
import ar.edu.unsam.proyecto_de_sofware.event_nexus.notification.listener.CreatedEntity
import java.util.concurrent.CopyOnWriteArrayList

open class GenericObserver<N: Notifiable, L: CreatedEntity<N>> {
    val listeners = CopyOnWriteArrayList<L>()

    fun subscribe(listener: L) {
        listeners.add(listener)
    }

    fun unsubscribe(listener: L) {
        listeners.remove(listener)
    }

    fun notify(entity: N, notification: Notification) {
        listeners.forEach { it.onCreated(entity, notification) }
    }
}
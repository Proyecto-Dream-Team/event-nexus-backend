package ar.edu.unsam.proyecto_de_sofware.event_nexus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync


@EnableAsync
@SpringBootApplication
class EventNexusApplication

fun main(args: Array<String>) {
	runApplication<EventNexusApplication>(*args)
}

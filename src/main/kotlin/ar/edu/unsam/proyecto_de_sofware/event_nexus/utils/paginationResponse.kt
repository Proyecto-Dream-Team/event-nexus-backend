package ar.edu.unsam.proyecto_de_sofware.event_nexus.utils

data class PageResponse<T>(
    val items: Iterable<T>,
    val hasNext: Boolean
)

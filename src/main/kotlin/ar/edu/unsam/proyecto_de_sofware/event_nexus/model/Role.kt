package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission


enum class Role(val jobName: String, val defaultPermissions:Set<Permission>){
    MOCK_PARA_JUGAR(
        jobName = "Human Resources",
        defaultPermissions = setOf()
    ),
    HR(
        jobName = "Human Resources",
        defaultPermissions = setOf(
            Permission.CREAR_EVENTO_SOCIAL, Permission.CREAR_EVENTO_CAPACITACION)
    ),
    DEV(
        jobName = "Softwate Developer",
        defaultPermissions = setOf(Permission.CREAR_EVENTO_SOCIAL, Permission.CREAR_EVENTO_DEPORTIVO)
    ),
    SUPERVISOR(
        jobName = "Supervisor",
        defaultPermissions = setOf(
            Permission.CREAR_EVENTO_SOCIAL, Permission.CREAR_EVENTO_DEPORTIVO, Permission.CREAR_EVENTO_CAPACITACION,
            Permission.CREATE_DIRECTIVE,
            Permission.CREATE_REPPORT
        )
    ),
    ADMIN(
        jobName = "Admin",
        defaultPermissions = Permission.values().toSet()
    )
}
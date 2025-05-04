package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission


enum class Role(val jobName: String, val defaultPermissions:Set<Permission>){
    MOCK_PARA_JUGAR(
        jobName = "Human Resources",
        defaultPermissions = setOf()
    ),
    HR(
        jobName = "Human Resources",
        defaultPermissions = setOf(Permission.CREATE_EVENT, Permission.SCHEDULE_EVENT)
    ),
    DEV(
        jobName = "Softwate Developer",
        defaultPermissions = setOf(Permission.CREATE_EVENT, Permission.CANCEL_EVENT)
    ),
    SUPERVISOR(
        jobName = "Supervisor",
        defaultPermissions = setOf(
            Permission.CREATE_EVENT, Permission.CANCEL_EVENT, Permission.SCHEDULE_EVENT,
            Permission.CREATE_DIRECTIVE,
            Permission.CREATE_REPPORT
        )
    ),
    ADMIN(
        jobName = "Admin",
        defaultPermissions = Permission.values().toSet()
    )
}
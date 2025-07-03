package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common.Permission


enum class Role(val jobName: String, val defaultPermissions:Set<Permission>){
    HHRR(
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
    ),
    QA(
        jobName = "Quality Assurance",
        defaultPermissions = setOf(Permission.CREAR_EVENTO_SOCIAL, Permission.CREAR_EVENTO_DEPORTIVO)
    ),
    DESIGNER(
        jobName = "Product Designer",
        defaultPermissions = setOf(Permission.CREAR_EVENTO_SOCIAL, Permission.CREAR_EVENTO_DEPORTIVO)
    ),
    MARKETING(
        jobName = "Marketing Specialist",
        defaultPermissions = setOf(Permission.CREAR_EVENTO_SOCIAL, Permission.CREAR_EVENTO_DEPORTIVO)
    ),
    FINANCE(
        jobName = "Finance Analyst",
        defaultPermissions = setOf(Permission.CREAR_EVENTO_SOCIAL, Permission.CREAR_EVENTO_DEPORTIVO)
    ),
    ANALYST(
        jobName = "Data Analyst",
        defaultPermissions = setOf(Permission.CREAR_EVENTO_SOCIAL, Permission.CREAR_EVENTO_DEPORTIVO)
    )
}
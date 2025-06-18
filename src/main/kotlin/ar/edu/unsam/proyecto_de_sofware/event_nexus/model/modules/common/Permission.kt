package ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.common


enum class PermissionType{
    EVENT, DIRECTIVE, REPPORT, ADMIN
}
enum class Permission(val permissionName:String, val type:PermissionType){
    CREAR_EVENTO_SOCIAL(
        permissionName="SOCIAL",
        type= PermissionType.EVENT
    ),
    CREAR_EVENTO_DEPORTIVO(
        permissionName="DEPORTIVO",
        type=PermissionType.EVENT
    ),
    CREAR_EVENTO_CAPACITACION(
        permissionName="CAPACITACION",
        type=PermissionType.EVENT
    ),
    CREAR_EVENTO_EJECUTIVO(
        permissionName="EJECUTIVO",
        type=PermissionType.EVENT
    ),
    CREAR_EVENTO_EQUIPO(
        permissionName="EQUIPO",
        type=PermissionType.EVENT
    ),
    CREATE_DIRECTIVE(
        permissionName="DIRECTIVA",
        type=PermissionType.DIRECTIVE
    ),
    CREATE_REPPORT(
        permissionName="REPPORT",
        type=PermissionType.REPPORT
    ),
    ADMIN_PERMISSION(
        permissionName="ADMIN",
        type=PermissionType.ADMIN
    )
}

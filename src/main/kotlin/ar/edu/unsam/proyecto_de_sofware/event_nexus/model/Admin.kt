package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

class Admin(name: String, lastname: String, role: Role) : User(name, lastname, role) {

    fun createUser(name: String, lastname: String, roleName: String, level: Int): User{
        //aca en el medio tiene el inser a la base
        return User(name, lastname, Role(roleName, level))
    }
}
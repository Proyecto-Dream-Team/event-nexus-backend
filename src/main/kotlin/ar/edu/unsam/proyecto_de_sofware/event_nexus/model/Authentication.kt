package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AvaliableInstance

class Authentication: AvaliableInstance{
    override var id: Int = 0
    var email:String = ""
    var username: String = ""
    var password: String = ""
    var role: String = ""
}


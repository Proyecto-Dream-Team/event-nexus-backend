package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Admin
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// AUTH CREDENTIALS
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun credentialsAdrian(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "adrian"
        password = encodedPassword
        role = Role.ADMIN
    }
}
fun credentialsDiego(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "diego"
        password = encodedPassword
        role = Role.SUPERVISOR
    }
}
fun credentialsMatias(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "mati"
        password = encodedPassword
        role = Role.DEV
    }
}
fun credentialsPica(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "pica"
        password = encodedPassword
        role = Role.HHRR
    }
}
fun credentialsValen(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "valen"
        password = encodedPassword
        role = Role.DEV
    }
}

fun credentialsTheo(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "theo"
        password = encodedPassword
        role = Role.SUPERVISOR
    }
}

fun credentialsLuana(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "luana"
        password = encodedPassword
        role = Role.DEV // Puedes cambiar el rol si lo necesitas
    }
}
fun credentialsFacundo(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "facundo"
        password = encodedPassword
        role = Role.QA // Puedes cambiar el rol
    }
}
fun credentialsSofia(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "sofia"
        password = encodedPassword
        role = Role.HHRR // Puedes cambiar el rol
    }
}
fun credentialsJoaquin(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "joaquin"
        password = encodedPassword
        role = Role.DEV // Puedes cambiar el rol
    }
}
fun credentialsEmilia(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "emilia"
        password = encodedPassword
        role = Role.SUPERVISOR // Puedes cambiar el rol
    }
}
fun credentialsIgnacio(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "ignacio"
        password = encodedPassword
        role = Role.DEV // Puedes cambiar el rol
    }
}
fun credentialsCamila(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "camila"
        password = encodedPassword
        role = Role.QA // Puedes cambiar el rol
    }
}
fun credentialsSantiago(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "santiago"
        password = encodedPassword
        role = Role.HHRR // Puedes cambiar el rol
    }
}
fun credentialsAgustina(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "agustina"
        password = encodedPassword
        role = Role.DEV // Puedes cambiar el rol
    }
}
fun credentialsNicolas(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "nicolas"
        password = encodedPassword
        role = Role.SUPERVISOR // Puedes cambiar el rol
    }
}
fun credentialsMicaela(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "micaela"
        password = encodedPassword
        role = Role.DESIGNER // Puedes cambiar el rol
    }
}
fun credentialsGaston(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "gaston"
        password = encodedPassword
        role = Role.MARKETING // Puedes cambiar el rol
    }
}
fun credentialsFlorencia(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "florencia"
        password = encodedPassword
        role = Role.FINANCE // Puedes cambiar el rol
    }
}
fun credentialsLucas(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "lucas"
        password = encodedPassword
        role = Role.ANALYST // Puedes cambiar el rol
    }
}
fun credentialsVictoria(encodedPassword:String): Credentials{
    return Credentials().apply {
        username = "victoria"
        password = encodedPassword
        role = Role.SUPERVISOR // Puedes cambiar el rol
    }
}

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EMPLOYEE ACCOUNTS
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun employeeAccountAdrian(credential: Credentials): Admin{
    return Admin().apply {
        name = "Adrian"
        lastname = "Perez"
        job = credential.role.name
        phone = "12341234"
        email = "adrian@mail.com"
        address = "calle posta 123"
        credentials = credential
        image = "https://es.terressens.com/samurai-katanas-espadas-japonesas-artes-marciales/assets/images/0594-sabre-d-inosuke-hashibira-manga-demon-slayer-kimetsu-no-yaiba-anime-1000x1000.jpg"
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountDiego(credential: Credentials): Employee{
    return Employee().apply {
        name = "Diego"
        lastname = "Lentz"
        job = credential.role.name
        phone = "12341234"
        email = "diego@mail.com"
        address = "calle falsa 123"
        image = "https://media.losandes.com.ar/p/db5ff1e064c64bf3d7d3f9cde690efa4/adjuntos/368/imagenes/100/003/0100003991/1000x0/smart/se-cumplen-4-anos-la-muerte-diego-maradona.png"
        credentials = credential
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountPica(credential: Credentials): Employee{
    return Employee().apply {
        name = "Pedro"
        lastname = "McGeraghty"
        job = credential.role.name
        phone = "12341234"
        email = "pica@mail.com"
        address = "Tambien calle falsa 123"
        credentials = credential
        image = "https://a.storyblok.com/f/178900/940x664/5e32bc09ff/kagurabachi-key.jpg/m/filters:quality(95)format(webp)"
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountMati(credential: Credentials): Employee{
    return Employee().apply {
        name = "Matias"
        lastname = "Diaz"
        job = credential.role.name
        phone = "12341234"
        email = "mati@mail.com"
        address = "Tambien calle falsa 123"
        credentials = credential
        image = "https://pbs.twimg.com/media/GgeCm3UXkAAo07Y?format=jpg&name=medium"
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}

fun employeeAccountValen(credential: Credentials): Employee{
    return Employee().apply {
        name = "Valen"
        lastname = "Pugliese"
        job = credential.role.name
        phone = "12341234"
        email = "valen@mail.com"
        address = "Tambien calle falsa 123"
        credentials = credential
        image = "https://static1.moviewebimages.com/wordpress/wp-content/uploads/2022/05/Izuku-Midoriya.jpg?q=50&fit=crop&w=1140&h=&dpr=1.5"
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountTheo(credential: Credentials): Employee{
    return Employee().apply {
        name = "Theo"
        lastname = "Narmontas"
        job = credential.role.name
        phone = "12341234"
        email = "theo@mail.com"
        address = "Tambien calle falsa 123"
        credentials = credential
        image = "https://static1.moviewebimages.com/wordpress/wp-content/uploads/2022/05/Yusuke-Urameshi---Yu-Yu-Hakusho.png?q=50&fit=crop&w=825&dpr=1.5"
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}

fun employeeAccountLuana(credential: Credentials): Employee{
    return Employee().apply {
        name = "Luana"
        lastname = "Giménez"
        job = credential.role.name
        phone = "1122334455"
        email = "luana@mail.com"
        address = "Avenida Siempre Viva 742"
        credentials = credential
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8t5Qv9hoArKjwgA25zZgoNoKuhbVU2zc6-A&s" // Anime: Luana from One Piece (fan art)
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountFacundo(credential: Credentials): Employee{
    return Employee().apply {
        name = "Facundo"
        lastname = "Pereyra"
        job = credential.role.name
        phone = "1133445566"
        email = "facundo@mail.com"
        address = "Calle Falsa 456"
        credentials = credential
        image = "https://img.freepik.com/fotos-premium/mujer-gorda-sentada-silla-come-bocadillo-bulimica-sobrepeso-estilo-vida-poco-saludable-obesidad_266732-4060.jpg?w=360" // Animal: Cat
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountSofia(credential: Credentials): Employee{
    return Employee().apply {
        name = "Sofía"
        lastname = "Martínez"
        job = credential.role.name
        phone = "1144556677"
        email = "sofia@mail.com"
        address = "Boulevard de los Sueños Rotos 890"
        credentials = credential
        image = "https://tse3.mm.bing.net/th/id/OIP.F3JZTzJnINfzfh934msBWAHaHa?pid=Api&P=0&h=180" // Persona
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountJoaquin(credential: Credentials): Employee{
    return Employee().apply {
        name = "Joaquín"
        lastname = "Fernández"
        job = credential.role.name
        phone = "1155667788"
        email = "joaquin@mail.com"
        address = "Pasaje Secreto 101"
        credentials = credential
        image = "https://tse4.mm.bing.net/th/id/OIP.5274E0fIbt4mwZ5zxkt_FQHaEM?pid=Api&P=0&h=180" // Anime: Joaquín from Encanto (fan art, if available) - Using a generic anime boy image as placeholder
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountEmilia(credential: Credentials): Employee{
    return Employee().apply {
        name = "Emilia"
        lastname = "González"
        job = credential.role.name
        phone = "1166778899"
        email = "emilia@mail.com"
        address = "Ruta Imaginaria Km 5"
        credentials = credential
        image = "https://tse1.mm.bing.net/th/id/OIP.5OetN6BY23rDYb2HOUBKsQHaEK?pid=Api&P=0&h=180" // Animal: Cat
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountIgnacio(credential: Credentials): Employee{
    return Employee().apply {
        name = "Ignacio"
        lastname = "Rodríguez"
        job = credential.role.name
        phone = "1177889900"
        email = "ignacio@mail.com"
        address = "Plaza de la Libertad s/n"
        credentials = credential
        image = "https://tse3.mm.bing.net/th/id/OIP.FjuGuAD8EkzhHw7PeoWiBwHaEo?pid=Api&P=0&h=180" // Persona
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountCamila(credential: Credentials): Employee{
    return Employee().apply {
        name = "Camila"
        lastname = "Díaz"
        job = credential.role.name
        phone = "1188990011"
        email = "camila@mail.com"
        address = "Calle de la Luna 321"
        credentials = credential
        image = "https://tse4.mm.bing.net/th/id/OIP.5ISqyXfA4mwVI6dIkf1y0wHaEK?pid=Api&P=0&h=180" // Anime: Camila from The Owl House (fan art, if available) - Using a generic female anime character
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountSantiago(credential: Credentials): Employee{
    return Employee().apply {
        name = "Santiago"
        lastname = "Hernández"
        job = credential.role.name
        phone = "1199001122"
        email = "santiago@mail.com"
        address = "Avenida del Sol 654"
        credentials = credential
        image = "https://tse1.mm.bing.net/th/id/OIP.3sV0g12paJHZXGorGN3__QHaIZ?pid=Api&P=0&h=180" // Animal: Golden Retriever
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountAgustina(credential: Credentials): Employee{
    return Employee().apply {
        name = "Agustina"
        lastname = "Ruiz"
        job = credential.role.name
        phone = "1100112233"
        email = "agustina@mail.com"
        address = "Calle del Cielo 789"
        credentials = credential
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3Q1RZKp3xPbsS2ogEcc0jQWPBHgBRLsClOA&s" // Persona
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountNicolas(credential: Credentials): Employee{
    return Employee().apply {
        name = "Nicolás"
        lastname = "Torres"
        job = credential.role.name
        phone = "1111223344"
        email = "nicolas@mail.com"
        address = "Camino Estrellado 123"
        credentials = credential
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4Ue4BAp0x32b9snB7lShEvzj-3vGKqzKfoA&s" // Anime: Nicolas Brown from Gangsta (fan art, if available) - Using a generic male anime character
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountMicaela(credential: Credentials): Employee{
    return Employee().apply {
        name = "Micaela"
        lastname = "Flores"
        job = credential.role.name
        phone = "1122334455"
        email = "micaela@mail.com"
        address = "Sendero Desconocido 456"
        credentials = credential
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXKfFtOmRQXnMAN5C1Fba2AcC3lCZ8jA1HnA&s" // Animal: Black Cat
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountGaston(credential: Credentials): Employee{
    return Employee().apply {
        name = "Gastón"
        lastname = "Vázquez"
        job = credential.role.name
        phone = "1133445566"
        email = "gaston@mail.com"
        address = "Avenida del Parque 789"
        credentials = credential
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAms_oSjfaGbMN9Ah7195cNGr9pWEH1vgpAw&s" // Persona
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountFlorencia(credential: Credentials): Employee{
    return Employee().apply {
        name = "Florencia"
        lastname = "Castro"
        job = credential.role.name
        phone = "1144556677"
        email = "florencia@mail.com"
        address = "Paseo de la Rosa 101"
        credentials = credential
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFps3ji-BNqSDNqhOUGBC5_FaibUaS7MgkXg&s" // Anime: Florencia from Free! (fan art, if available) - Using a generic female anime character
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountLucas(credential: Credentials): Employee{
    return Employee().apply {
        name = "Lucas"
        lastname = "Benítez"
        job = credential.role.name
        phone = "1155667788"
        email = "lucas@mail.com"
        address = "Calle del Bosque 321"
        credentials = credential
        image = "https://i.pinimg.com/736x/64/7e/24/647e24bc03fd9fd5847e5cd49ee37d72.jpg" // Anime: Ash Ketchum (Pokémon)
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}
fun employeeAccountVictoria(credential: Credentials): Employee{
    return Employee().apply {
        name = "Victoria"
        lastname = "Silva"
        job = credential.role.name
        phone = "1166778899"
        email = "victoria@mail.com"
        address = "Río de la Plata 007"
        credentials = credential
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjUznpckAtIsABqyzm7laBAE5bAxc-w_0G5A&s" // Animal: Husky
        permissions = credential.role.defaultPermissions.toMutableSet()
    }
}

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Directives
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

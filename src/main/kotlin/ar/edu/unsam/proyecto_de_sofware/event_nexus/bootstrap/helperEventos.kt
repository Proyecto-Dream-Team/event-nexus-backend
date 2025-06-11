package ar.edu.unsam.proyecto_de_sofware.event_nexus.bootstrap

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.Event
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.modules.base.events.EventType
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// EVENTS
// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

fun event01(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Conferencia Anual de Innovación Tecnológica"
        description = "Prepárense para una jornada intensa de charlas y talleres sobre IA, blockchain y computación cuántica. Contaremos con líderes de la industria y oportunidades de networking. Traigan sus dispositivos y energía para absorber conocimientos. ¡No se lo pierdan!"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.WEEKS).plus(3, ChronoUnit.DAYS).plus(18, ChronoUnit.HOURS)
        type = EventType.CAPACITACION
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event02(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Gran Maratón Solidario por la Infancia"
        description = "Únete a nuestra carrera anual para recaudar fondos para niños necesitados. Categorías para todas las edades y niveles. Inscríbete y sé parte de esta noble causa que transformará vidas. Cada kilómetro suma. Habrá sorpresas y un gran ambiente familiar."
        expirationDate = LocalDateTime.now().plus(5, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS)
        type = EventType.BENEFICO
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event03(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Noche de Estrellas y Observación Astronómica"
        description = "Velada mágica bajo el cielo con telescopios y expertos. Observa constelaciones, planetas y galaxias. Oportunidad única para maravillarse con el cosmos. Se recomienda llevar ropa abrigada y una manta para disfrutar plenamente."
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.WEEKS).plus(1, ChronoUnit.DAYS).plus(10, ChronoUnit.HOURS)
        type = EventType.CULTURAL
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event04(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Workshop Intensivo de Marketing Digital"
        description = "Taller de dos días para potenciar tu presencia online: SEO, SEM, contenidos y redes sociales. Aprende a construir una marca y generar leads. Cupos limitados, ¡asegura tu lugar! Incluye material de apoyo y certificación al finalizar."
        expirationDate = LocalDateTime.now().plus(4, ChronoUnit.DAYS).plus(15, ChronoUnit.HOURS).plus(6, ChronoUnit.HOURS)
        type = EventType.CAPACITACION
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event05(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Torneo Amistoso de Fútbol 5 entre Empresas"
        description = "Torneo de confraternidad para equipos de empresas. Fomentamos compañerismo y espíritu deportivo. Habrá trofeos y un asado de cierre. Inscríbete con tus colegas y demuestra tu talento en la cancha."
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.WEEKS).plus(2, ChronoUnit.DAYS)
        type = EventType.DEPORTIVO
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event06(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Retiro de Bienestar y Mindfulness en la Naturaleza"
        description = "Escapa del ajetreo y sumérgete en un fin de semana de paz y renovación. Sesiones de meditación, yoga y conexión con la naturaleza. Aprende técnicas para manejar el estrés. Incluye alojamiento y comidas saludables."
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.WEEKS).plus(4, ChronoUnit.DAYS).plus(22, ChronoUnit.HOURS)
        type = EventType.SALUD
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event07(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Networking para Jóvenes Emprendedores"
        description = "Velada informal y dinámica para que jóvenes talentos conecten, intercambien ideas y encuentren socios. Mesas redondas con emprendedores exitosos. Prepara tu pitch y tarjetas de presentación."
        expirationDate = LocalDateTime.now().plus(6, ChronoUnit.DAYS).plus(11, ChronoUnit.HOURS)
        type = EventType.SOCIAL
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event08(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Jornada de Puertas Abiertas en el Museo de Arte Moderno"
        description = "Descubre nuevas colecciones y obras de arte contemporáneas. Disfruta de visitas guiadas gratuitas y actividades interactivas para toda la familia. Sumérgete en la cultura local. Habrá una feria de diseño en el patio interior."
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.WEEKS).plus(5, ChronoUnit.DAYS).plus(8, ChronoUnit.HOURS)
        type = EventType.CULTURAL
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event09(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Sesión Estratégica Trimestral del Directorio"
        description = "Reunión privada donde el directorio evaluará resultados y definirá objetivos clave para el próximo período. Se analizarán indicadores financieros, estrategias de mercado y planes de expansión."
        expirationDate = LocalDateTime.now().plus(3, ChronoUnit.DAYS).plus(20, ChronoUnit.HOURS)
        type = EventType.EJECUTIVO
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event10(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Celebración del Aniversario de la Compañía"
        description = "Celebra con nosotros un año más de logros. Cena especial, música en vivo y reconocimiento a empleados. Una ocasión perfecta para agradecer el esfuerzo del equipo y fortalecer lazos. ¡Unidos somos más fuertes!"
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.WEEKS).plus(6, ChronoUnit.DAYS).plus(13, ChronoUnit.HOURS)
        type = EventType.EQUIPO
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event11(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Clase Magistral de Cocina Mediterránea"
        description = "Aprende los secretos de la gastronomía mediterránea con un chef reconocido. Clase práctica para preparar platos tradicionales con ingredientes frescos. Degusta tus creaciones y lleva a casa un recetario."
        expirationDate = LocalDateTime.now().plus(7, ChronoUnit.DAYS).plus(1, ChronoUnit.HOURS)
        type = EventType.SOCIAL
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event12(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Taller de Primeros Auxilios y RCP para la Comunidad"
        description = "Taller esencial para aprender técnicas básicas de primeros auxilios y RCP que pueden salvar vidas. Impartido por profesionales de la salud. Te dará herramientas y confianza para actuar en emergencias."
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.WEEKS).plus(4, ChronoUnit.DAYS)
        type = EventType.SALUD
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event13(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Seminario Web: 'El Futuro del Trabajo Remoto'"
        description = "Explora tendencias, herramientas y desafíos del trabajo a distancia. Discutiremos mejores prácticas para productividad, comunicación y bienestar en un entorno virtual. Ideal para gerentes y empleados."
        expirationDate = LocalDateTime.now().plus(2, ChronoUnit.WEEKS).plus(3, ChronoUnit.HOURS)
        type = EventType.CAPACITACION
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event14(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Partido de Básquetbol Intercolegial"
        description = "Apoya a tu equipo favorito en un emocionante encuentro de básquetbol. Los mejores talentos juveniles en la cancha. Habrá comida y bebida, y un ambiente lleno de energía. Una tarde de deporte y diversión para toda la familia."
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.WEEKS).plus(5, ChronoUnit.DAYS).plus(7, ChronoUnit.HOURS)
        type = EventType.DEPORTIVO
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event15(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Reunión de Integración de Nuevos Empleados"
        description = "Damos la bienvenida a los nuevos miembros del equipo con una actividad divertida y relajada. Conoce a colegas de otros departamentos, comparte experiencias y siéntete parte de la cultura de la empresa."
        expirationDate = LocalDateTime.now().plus(4, ChronoUnit.DAYS).plus(19, ChronoUnit.HOURS)
        type = EventType.EQUIPO
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event16(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Feria Artesanal y de Emprendedores Locales"
        description = "Descubre tesoros únicos hechos a mano y apoya a emprendedores locales. Encontrarás joyería, textiles, productos gourmet y arte. Disfruta del ambiente festivo, música en vivo y comida."
        expirationDate = LocalDateTime.now().plus(1, ChronoUnit.WEEKS).plus(2, ChronoUnit.DAYS).plus(11, ChronoUnit.HOURS)
        type = EventType.CULTURAL
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event17(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Sesión de Yoga y Meditación al Amanecer"
        description = "Comienza tu día con energía y serenidad. Sesión al aire libre para conectar con cuerpo y mente bajo el sol. Apta para todos los niveles. Trae tu esterilla y actitud positiva."
        expirationDate = LocalDateTime.now().plus(6, ChronoUnit.DAYS).plus(23, ChronoUnit.HOURS)
        type = EventType.SALUD
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}

fun event18(creatorEmployee: Employee, participantsEmployees:Set<Employee>): Event {
    return Event().apply {
        title = "Comida de Fin de Año del Equipo de Ventas"
        description =
            "Ocasión especial para celebrar un año exitoso de trabajo. Disfruta de una deliciosa comida, recuerda los mejores momentos y brinda por el próximo año. Momento para relajarse y disfrutar en compañía."
        expirationDate =
            LocalDateTime.now().plus(1, ChronoUnit.WEEKS).plus(3, ChronoUnit.HOURS).plus(4, ChronoUnit.DAYS)
        type = EventType.EQUIPO
        creator = creatorEmployee
        participants = participantsEmployees.toMutableSet()
        public = true
    }
}
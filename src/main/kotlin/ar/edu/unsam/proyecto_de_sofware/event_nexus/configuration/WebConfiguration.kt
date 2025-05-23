package ar.edu.unsam.proyecto_de_sofware.event_nexus.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

//@Configuration
//class WebConfig : WebMvcConfigurer {
//
//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**") // Apply to all endpoints
//            .allowedOrigins("http://localhost:5173", "http://127.0.0.1:3001") // Your frontend URL(s)
//            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
//            .allowedHeaders("*") // Allowed headers
//            .allowCredentials(true) // If you're sending cookies or authorization headers
//            .maxAge(3600) // How long the preflight request can be cached
//    }
//}
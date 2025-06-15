package ar.edu.unsam.proyecto_de_sofware.event_nexus.security

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.UserRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.security.filter.JwtFiter
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class ApplicationSecutityConfiguration(
    @Autowired val jwtUtil: JwtUtil
) {
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
//            .cors { it.disable() }
            .csrf { it.disable() }
            .httpBasic(Customizer.withDefaults())
            .exceptionHandling(Customizer.withDefaults())
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorizeHttpRequests ->
                //Publicos
                authorizeHttpRequests.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/error").permitAll()

                // ASD
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/module").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")

                // NOTIFICATION
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/notification").permitAll()
//                    .hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/notification/employee").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")


                // USER
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/user/home").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/user/header").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/user/profile").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/user/detail").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.PUT, "/user/profile").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/user/available").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.PUT, "/user/img").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/user/permissions/*").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/user").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")


                //ADMIN
                authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/admin/create-user").hasRole(Role.ADMIN.toString())
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/admin/edit-user/*").hasRole(Role.ADMIN.toString())
                authorizeHttpRequests.requestMatchers(HttpMethod.PUT, "/admin/edit-user").hasRole(Role.ADMIN.toString())
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/admin/permissions-role").hasRole(Role.ADMIN.toString())
                authorizeHttpRequests.requestMatchers(HttpMethod.PUT, "/admin/register").permitAll()
                authorizeHttpRequests.requestMatchers(HttpMethod.PUT, "/admin/recovery").permitAll()
                authorizeHttpRequests.requestMatchers(HttpMethod.DELETE, "/admin/delete/user/*").hasRole(Role.ADMIN.toString())

                //EVENT
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/event/all").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/event/available").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/event/title").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/event/type/*").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/event/created").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/event/invited").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/event/create").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/event/create").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/event/type/all").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/event/join-leave").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.PUT, "/event").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.DELETE, "/event").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")

                //DIRECTIVE
                authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/directive").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")
                authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/directive/create").hasAnyRole("ADMIN", "SUPERVISOR", "DEV" , "HR")


                //Analytics controller

                //SWAGER
                authorizeHttpRequests.requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/v3/api-docs",
                    "/swagger-resources/**",
                    "/webjars/**"
                ).permitAll()

                //Default
                authorizeHttpRequests.anyRequest().denyAll()
            }
            .addFilterBefore(JwtFiter(jwtUtil), BasicAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }

    @Bean
    fun authenticationProvider(authRepo: AuthRepository): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(this.passwordEncoder())
        provider.setUserDetailsService(this.userDetailService(authRepo, passwordEncoder()))
        return provider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return Argon2PasswordEncoder(128, 255, 1, 1024, 1)
    }

    @Bean
    fun userDetailService(authRepo: AuthRepository, passwordEncoder: PasswordEncoder): UserDetailsService {
        return AuthService(authRepo, passwordEncoder)
    }

    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:5173", "http://localhost:3001")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring()
                .requestMatchers("/swagger-ui/", "/v3/api-docs*/")
        }
    }
}
package ar.edu.unsam.proyecto_de_sofware.event_nexus.integration.controller

import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginRequest
import ar.edu.unsam.proyecto_de_sofware.event_nexus.dto.LoginResponse
import ar.edu.unsam.proyecto_de_sofware.event_nexus.exceptions.InvalidCredentialsException
import ar.edu.unsam.proyecto_de_sofware.event_nexus.repository.AuthRepository
import ar.edu.unsam.proyecto_de_sofware.event_nexus.security.JwtUtil
import ar.edu.unsam.proyecto_de_sofware.event_nexus.service.AuthService
import com.auth0.jwt.interfaces.Claim
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.jsonwebtoken.Claims
import io.kotest.core.spec.style.AnnotationSpec
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertSame


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Dado un controller de Login")
class AuthControllerTest(
    @Autowired var authRepo: AuthRepository,
    @Autowired var authService: AuthService,
    @Autowired var jwtUtil: JwtUtil,
    @Autowired var mockMvc: MockMvc,
) {

//    val testFactory = TestFactory(authService, passengerService, driverService ,jwtUtil)
//    val tokenDriver = testFactory.generateTokenDriverTest("simple")
//    val tokenPassenger = testFactory.generateTokenPassengerTest("adrian")

    private fun perform(mockMvcRequestBuilder: MockHttpServletRequestBuilder): ResultActions{
        return mockMvc.perform(mockMvcRequestBuilder)
    }

    private fun mockPost(uriPath:String): MockHttpServletRequestBuilder{
        return MockMvcRequestBuilders.post(uriPath)
    }

    private fun toJson(objectToParse:Any):String{
        return ObjectMapper().writeValueAsString(objectToParse)
    }

    private fun validCredentials():Map<String, String>{
        return mapOf("username" to "adrian", "password" to "adrian")
    }

    private fun invalidCredentials():Map<String, String>{
        return mapOf("username" to "invalid_username", "password" to "invalid_password")
    }

    private fun parseToLoginResponse(json:String): LoginResponse{
        val mapper = ObjectMapper().registerKotlinModule()
        return mapper.readValue(json, LoginResponse::class.java)
    }


    @Test
    fun loginWithoutRequestBody(){
        this.perform( mockMvcRequestBuilder =
            this.mockPost("/login")
        ).andExpect {
            assertEquals(expected = HttpStatus.BAD_REQUEST.value(), actual = it.response.status)
            assertEquals(expected = HttpMessageNotReadableException::class, actual = it.resolvedException!!::class)
        }
    }

    @Test
    fun loginWithInvalidUsername(){
        val loginRequest: LoginRequest = LoginRequest(
            username = this.invalidCredentials()["username"]!!,
            password = "" // No me interesa el password en este caso, ya que no pasa del username
        )
        this.perform( mockMvcRequestBuilder =
            this.mockPost("/login")
                .contentType("application/json")
                .content(this.toJson(objectToParse = loginRequest))
        ).andExpect {
            assertEquals(expected = HttpStatus.UNAUTHORIZED.value(), actual = it.response.status)
            assertSame(expected = InvalidCredentialsException()::class, actual = it.resolvedException!!::class)
        }
    }

    @Test
    fun loginWithInvalidPassword(){
        val loginRequest: LoginRequest = LoginRequest(
            username = this.validCredentials()["username"]!!,
            password = this.invalidCredentials()["password"]!!
        )
        this.perform( mockMvcRequestBuilder =
            this.mockPost("/login")
                .contentType("application/json")
                .content(this.toJson(objectToParse = loginRequest))
        ).andExpect {
            assertEquals(expected = HttpStatus.UNAUTHORIZED.value(), actual = it.response.status)
            assertSame(expected = InvalidCredentialsException()::class, actual = it.resolvedException!!::class)
        }
    }

    @Test
    fun sucessfullLoginDriver(){
        val loginRequest: LoginRequest = LoginRequest(username="premium", password="premium")
        this.perform( mockMvcRequestBuilder =
            this.mockPost("/login")
                .contentType("application/json")
                .content(this.toJson(objectToParse = loginRequest))
        ).andExpect {
            val parsedLoginResponse: LoginResponse = this.parseToLoginResponse(it.response.contentAsString)
            assertNotNull(actual = parsedLoginResponse.token)
            val claims: Claims = jwtUtil.getAllClaims(jwtToken = parsedLoginResponse.token!!)
            assertEquals(expected = HttpStatus.OK.value(), actual = it.response.status)
            assertEquals(expected = 1, actual = claims["userID"])
            assertEquals(expected = listOf("ROLE_DRIVER"), actual = claims["rol"])

        }
    }


}
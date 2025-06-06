package ar.edu.unsam.proyecto_de_sofware.event_nexus.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class Credentials : UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(length = 255)
    private lateinit var username: String

    @Column(length = 550)
    private lateinit var password: String

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    lateinit var role: Role

    @Column(nullable = false)
    private var accountNonExpired: Boolean = true

    @Column(nullable = false)
    private var accountNonLocked: Boolean = true

    @Column(nullable = false)
    private var credentialsNonExpired: Boolean = true

    @Column(nullable = false)
    private var enabled: Boolean = true

    fun validateRegister(): Boolean {
        return password == "back" && username == "back"
    }

    fun setNewCredentials(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun validateUsername(username: String): Boolean {
        return  username == this.username
    }

    override fun getPassword(): String = this.password
    fun setPassword(encodedPassword: String) {
        this.password = encodedPassword
    }

    override fun getUsername(): String = this.username
    fun setUsername(username: String) {
        this.username = username
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_${this.role.name}"))
    }

    override fun isAccountNonExpired(): Boolean = this.accountNonExpired
    override fun isAccountNonLocked(): Boolean = this.accountNonLocked
    override fun isCredentialsNonExpired(): Boolean = this.credentialsNonExpired
    override fun isEnabled(): Boolean = this.enabled
    fun lockAccount() {
        accountNonLocked = false
    }

    fun unlockAccount() {
        accountNonLocked = true
    }

    fun expireAccount() {
        accountNonExpired = false
    }

    fun expireCredentials() {
        credentialsNonExpired = false
    }

    fun disable() {
        enabled = false
    }

    fun enable() {
        enabled = true
    }
}


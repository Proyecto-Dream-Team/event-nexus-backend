package ar.edu.unsam.proyecto_de_sofware.event_nexus.unit

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Credentials
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Role
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeExactly
import io.kotest.matchers.equals.shouldBeEqual

class CredentialsSpec: DescribeSpec( {
    isolationMode = IsolationMode.InstancePerTest

    describe(name="Dadas unas credenciales") {
        val credentials: Credentials = Credentials()


        describe(name="Tienen username y password") {
            val username:String = "username"
            val password:String = "password"
            credentials.setNewCredentials(username = username, password = password)
            credentials.username shouldBeEqual username
            credentials.password shouldBeEqual password

            it(name="Puede modificar username"){
                val newUsername:String = "newUsername"
                val newPassword:String = " newPassword"
                credentials.setNewCredentials(username = newUsername, password = newPassword)
                credentials.username shouldBeEqual newUsername
                credentials.password shouldBeEqual newPassword
            }
        }

        describe(name="Tiene flags asociadas") {
            describe(name="CUENTA HABILITADA"){
                it(name="Siempre true por defecto") {
                    credentials.isEnabled shouldBeEqual true
                }
                it(name="Alternar entre enable y disable") {
                    credentials.isEnabled shouldBeEqual true
                    credentials.disable()
                    credentials.isEnabled shouldBeEqual false
                    credentials.enable()
                    credentials.isEnabled shouldBeEqual true
                }
            }

            describe(name="CUENTA NO EXPIRADA"){
                it(name="DEFAULT - TRUE") {
                    credentials.isAccountNonExpired shouldBeEqual true
                }
                it(name="Una vez expirada no existe comportamiento para revertirlo") {
                    credentials.isAccountNonExpired shouldBeEqual true
                    credentials.expireAccount()
                    credentials.isAccountNonExpired shouldBeEqual false
                }
            }

            describe(name="CUENTA NO BLOQUEADA") {
                it(name="DEFAULT - TRUE") {
                    credentials.isAccountNonLocked shouldBeEqual true
                }
                it(name="Alternar entre locked y unlocked") {
                    credentials.isAccountNonLocked shouldBeEqual true
                    credentials.lockAccount()
                    credentials.isAccountNonLocked shouldBeEqual false
                    credentials.unlockAccount()
                    credentials.isAccountNonLocked shouldBeEqual true
                }
            }
            describe(name="CREDENCIALES NO EXPIRADAS") {
                it(name="DEFAULT - TRUE") {
                    credentials.isCredentialsNonExpired shouldBeEqual true
                }
                it(name="Una vez expirada no existe comportamiento para revertirlo") {
                    credentials.isCredentialsNonExpired shouldBeEqual true
                    credentials.expireCredentials()
                    credentials.isCredentialsNonExpired shouldBeEqual false
                }
            }
        }

        it(name="Tiene una autoridad asociada a un Rol") {
            val employeeRole: Role = Role.ADMIN
            credentials.apply { role = employeeRole }
            credentials.role shouldBeEqual employeeRole
        }
    }


})
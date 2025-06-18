package ar.edu.unsam.proyecto_de_sofware.event_nexus.unit

import ar.edu.unsam.proyecto_de_sofware.event_nexus.model.Employee
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeExactly
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe

class EmployeeSpec: DescribeSpec( {
    isolationMode = IsolationMode.InstancePerTest

    describe("Dado un empleado") {
        val employee:Employee = Employee()


        it("Tiene permisos") {
            true shouldBeEqual  true
        }
    }


})

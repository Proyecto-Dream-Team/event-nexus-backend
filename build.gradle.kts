plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.serialization") version "1.6.0"
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
	jacoco
	war
}

group = "ar.edu.unsam.proyecto-de-sofware"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	val kotestVersion = "5.8.0"

	// básicos de cualquier proyecto Spring Boot
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

	// testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.13.9")
	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
}

jacoco {
	toolVersion = "0.8.12"
}

tasks.jacocoTestReport {
	classDirectories.setFrom(
		files(classDirectories.files.map {
			fileTree(it) {
				exclude("**/config/**", "**/entity/**", "**/*Application*.*", "**/ServletInitializer.*")
			}
		})
	)
	reports {
		xml.required.set(true)
		csv.required.set(true)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}
}

tasks.register("runOnGitHub") {
	dependsOn("jacocoTestReport")
	group = "custom"
	description = "$ ./gradlew runOnGitHub # runs on GitHub Action"
}

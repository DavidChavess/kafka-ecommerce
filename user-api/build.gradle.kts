import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.davidchaves"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	maven {
		name = "GitHubPackages"
		url = uri("https://maven.pkg.github.com/DavidChavess/kafka-package")
		credentials {
			username = project.property("usernameCredentials").toString()
			password = project.property("passwordCredentials").toString()
		}
	}
}

dependencies {
	implementation("org.xerial:sqlite-jdbc:3.42.0.0")
	implementation("com.davidchavess:kafka-package:1.7.0")

	implementation("org.springframework.boot:spring-boot-starter-web"){
		exclude(module = "logback-classic")
	}

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

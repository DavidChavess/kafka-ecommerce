package main

import domain.usecase.ConsumerService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserApiApplication(private val consumers: List<ConsumerService>) : CommandLineRunner {
        override fun run(vararg args: String?) {
        consumers.forEach(ConsumerService::run)
    }
}

fun main(args: Array<String>) {
    runApplication<UserApiApplication>(*args)
}

package main

import data.AddUserDataBase
import data.BatchUser
import domain.User
import domain.usecase.BatchUserUseCase
import domain.usecase.ConsumerService
import infra.BatchUserKafkaConsumer
import infra.OrderKafkaConsumer
import infra.UserKafkaProducer
import infra.UserSqLiteDataBase
import kafka.ProducerUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.Connection
import java.sql.DriverManager

@Configuration
class Config<T> {
    @Bean
    fun connection(): Connection = DriverManager.getConnection("jdbc:sqlite:target/users_database.db")

    @Bean
    fun producerUtil(): ProducerUtil<T> = ProducerUtil()

    @Bean
    fun addUserDataBase(con: Connection): ConsumerService {
        return AddUserDataBase(OrderKafkaConsumer(), UserSqLiteDataBase(con))
    }

    @Bean
    fun batchUser(con: Connection, producer: ProducerUtil<User>): ConsumerService {
        return BatchUser(BatchUserKafkaConsumer(),UserSqLiteDataBase(con), UserKafkaProducer(producer))
    }
}
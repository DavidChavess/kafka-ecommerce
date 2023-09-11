package infra

import data.protocols.UserConsumer
import domain.User
import kafka.Message
import service.ConsumerService
import service.RunnerService

class UserKafkaConsumer : UserConsumer {

    override fun run(consumer: (User) -> Unit) {
        val orderService = object : ConsumerService<User> {
            override fun topic(): String = "USER_GENERATE_READING_REPORT"
            override fun consumerGroup(): String = UserKafkaConsumer::class.java.name
            override fun parse(message: Message<User>): Message<User> {
                consumer(message.payload)
                return message
            }
        }
        RunnerService(orderService).start(5)
    }
}
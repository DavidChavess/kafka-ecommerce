package infra

import data.protocols.BatchUserConsumer
import kafka.Message
import service.ConsumerService
import service.RunnerService

class BatchUserKafkaConsumer : BatchUserConsumer {

    override fun run(consumer: (topics: List<String>) -> Unit) {
        val batchUserService = object : ConsumerService<List<String>> {
            override fun topic(): String = "BATCH_USERS"
            override fun consumerGroup(): String = "UserApi-${BatchUserKafkaConsumer::class.java.name}"
            override fun parse(message: Message<List<String>>): Message<List<String>> {
                consumer(message.payload)
                return message
            }
        }
        RunnerService(batchUserService).start(1)
    }
}

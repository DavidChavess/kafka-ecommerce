import kafka.Message
import service.ConsumerService
import service.RunnerService

fun main() {
    val logService = object : ConsumerService<String> {
        override fun topic(): String = "ECOMMERCE.*"
        override fun consumerGroup(): String = "LogConsumer"
        override fun parse(message: Message<String>): Message<String> {
            println(message)
            return message
        }
    }
    RunnerService(logService).start(5)
}
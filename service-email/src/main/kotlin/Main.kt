import kafka.Message
import service.ConsumerService
import service.RunnerService

fun main() {
    val emailService = object : ConsumerService<String> {
        override fun topic(): String = "ECOMMERCE_SEND_EMAIL"
        override fun consumerGroup(): String = "EmailGroup"
        override fun parse(message: Message<String>): Message<String> {
            println("Sending email to ${message.correlationId}, content = ${message.payload}")
            return message
        }
    }
    RunnerService(emailService).start(5)
}
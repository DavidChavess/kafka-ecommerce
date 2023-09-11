package infra

import data.protocols.EmailProducer
import domain.User
import kafka.Message
import kafka.ProducerUtil
import java.io.File

class EmailKafkaProducer(
    private val emailProducer: ProducerUtil<String> = ProducerUtil()
) : EmailProducer {

    override fun send(user: User, report: File) {
        emailProducer.sendAsync(
            "ECOMMERCE_SEND_EMAIL",
            user.email,
            Message(user.email, "generated report, attached in: ${report.absolutePath}.")
        )
    }
}
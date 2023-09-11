package infra

import data.protocols.UserProducer
import domain.User
import kafka.Message
import kafka.ProducerUtil
import java.util.UUID

class UserKafkaProducer(
    private val producer: ProducerUtil<User>
) : UserProducer {

    override fun send(topic: String, user: User) {
        producer.sendAsync(topic, user.id, Message(UUID.randomUUID().toString(), user))
    }
}

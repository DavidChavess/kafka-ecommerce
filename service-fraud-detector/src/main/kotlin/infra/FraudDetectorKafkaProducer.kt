package infra

import data.protocols.FraudDetectorProducer
import domain.MessageOrder
import domain.Order
import kafka.Message
import kafka.ProducerUtil

class FraudDetectorKafkaProducer : FraudDetectorProducer {
    private val producer = ProducerUtil<Order>()

    override fun run(topic: String, message: MessageOrder) {
        producer.sendAsync(topic, message.payload.userId, Message(message.correlationId, message.payload))
    }
}
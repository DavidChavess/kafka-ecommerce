package infra

import data.protocols.FraudDetectorConsumer
import domain.MessageOrder
import domain.Order
import kafka.Message
import service.ConsumerService
import service.RunnerService

class FraudDetectorKafkaConsumer : FraudDetectorConsumer {

    override fun run(topic: String, consumer: (message: MessageOrder) -> Unit) {
        val orderService = object : ConsumerService<Order> {
            override fun topic(): String = "ECOMMERCE_NEW_ORDER"
            override fun consumerGroup(): String = "5-${FraudDetectorKafkaConsumer::class.java.name}}"
            override fun parse(message: Message<Order>): Message<Order> {
                consumer(MessageOrder(message.correlationId, message.payload))
                return message
            }
        }
        RunnerService(orderService).start(5)
    }
}
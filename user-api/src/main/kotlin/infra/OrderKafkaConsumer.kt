package infra

import data.protocols.OrderConsumer
import domain.Order
import kafka.Message
import service.ConsumerService
import service.RunnerService

class OrderKafkaConsumer : OrderConsumer {

    override fun run(consumer: (Order) -> Unit) {
        val orderService = object : ConsumerService<Order> {
            override fun topic(): String = "ECOMMERCE_NEW_ORDER"
            override fun consumerGroup(): String = "UserApi-${OrderKafkaConsumer::class.java.name}"
            override fun parse(message: Message<Order>): Message<Order> {
                consumer(message.payload)
                return message
            }
        }
        RunnerService(orderService).start(1)
    }
}

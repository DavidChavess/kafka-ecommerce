package data

import data.protocols.FraudDetectorConsumer
import data.protocols.FraudDetectorProducer
import domain.MessageOrder
import domain.usecase.FraudDetectorUseCase
import java.util.UUID

class FraudDetectorMessage(
    private val producer: FraudDetectorProducer,
    private val consumer: FraudDetectorConsumer
) : FraudDetectorUseCase {

    override fun run() {
        val correlationId = UUID.randomUUID()
        consumer.run("ECOMMERCE_NEW_ORDER") { message ->
            if (message.payload.isFraud()) {
                MessageOrder(message.continueWithCorrelationId("OrderRejected($correlationId)"), message.payload)
                    .let { producer.run("ECOMMERCE_ORDER_REJECTED", it) }
            } else {
                MessageOrder(message.continueWithCorrelationId("OrderApproved($correlationId)"), message.payload)
                    .let { producer.run("ECOMMERCE_ORDER_APPROVED", it) }
            }
        }
    }
}
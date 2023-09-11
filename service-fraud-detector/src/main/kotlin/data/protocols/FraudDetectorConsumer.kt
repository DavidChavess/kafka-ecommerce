package data.protocols

import domain.MessageOrder

interface FraudDetectorConsumer {
    fun run(topic: String, consumer: (message: MessageOrder) -> Unit)
}

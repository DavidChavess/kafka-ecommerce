package data.protocols

import domain.MessageOrder

interface FraudDetectorProducer {
    fun run(topic: String, message: MessageOrder)
}

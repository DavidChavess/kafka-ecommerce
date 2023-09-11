package com.davidchaves.orderapi.service.impl

import com.davidchaves.orderapi.model.ProducerMessage
import com.davidchaves.orderapi.service.Producer
import kafka.Message
import kafka.ProducerUtil
import org.springframework.stereotype.Service

@Service
class ProducerImpl<T>(
    private val producerKafka: ProducerUtil<T>
) : Producer<T> {

    override fun send(topic: String, producerMessage: ProducerMessage<T>, key: String?) {
        val message = Message(producerMessage.id, producerMessage.value)
        if (key != null)
            producerKafka.sendAsync(topic, key, message)
        else
            producerKafka.sendAsync(topic, message = message)
    }
}
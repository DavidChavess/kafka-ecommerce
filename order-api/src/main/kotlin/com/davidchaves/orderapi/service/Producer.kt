package com.davidchaves.orderapi.service

import com.davidchaves.orderapi.model.ProducerMessage

interface Producer<MessageType> {
    fun send(topic: String, producerMessage: ProducerMessage<MessageType>, key: String? = null)
}
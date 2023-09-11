package com.davidchaves.orderapi.model

data class ProducerMessage<T>(
    val id: String,
    val value: T
)
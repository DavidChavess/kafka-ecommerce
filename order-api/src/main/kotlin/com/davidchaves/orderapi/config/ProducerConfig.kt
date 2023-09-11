package com.davidchaves.orderapi.config

import kafka.ProducerUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProducerConfig<T> {

    @Bean
    fun producerUtil(): ProducerUtil<T> {
        return ProducerUtil<T>()
    }
}
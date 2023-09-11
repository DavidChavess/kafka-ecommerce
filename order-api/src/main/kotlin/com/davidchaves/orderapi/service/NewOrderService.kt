package com.davidchaves.orderapi.service

import domain.Order

interface NewOrderService {
    fun wasProcessed(order: Order): Boolean
    fun add(order: Order)
}
package com.davidchaves.orderapi.controller

import com.davidchaves.orderapi.model.ProducerMessage
import com.davidchaves.orderapi.service.NewOrderService
import com.davidchaves.orderapi.service.Producer
import domain.Order
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/orders")
class OrderController(
    private val newOrderService: NewOrderService,
    private val producerOrder: Producer<Order>
) {

    @GetMapping
    fun createOrder(order: Order) {
        if (newOrderService.wasProcessed(order)) {
            println("Order was processed")
            return
        }
        println("processing new order...")
        val id = UUID.randomUUID()
        producerOrder.send("ECOMMERCE_NEW_ORDER", ProducerMessage("CreateNewOrder($id)", order), order.userId)
        newOrderService.add(order)
    }
}
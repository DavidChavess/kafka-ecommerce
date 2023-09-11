package data.protocols

import domain.Order

interface OrderConsumer {
    fun run(consumer: (Order) -> Unit)
}

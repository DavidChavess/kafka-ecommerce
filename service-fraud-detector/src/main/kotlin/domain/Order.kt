package domain

import java.math.BigDecimal

data class Order(
    val userId: String,
    val orderId: String,
    val total: BigDecimal
) {
    fun isFraud(): Boolean {
        return false
    }
}
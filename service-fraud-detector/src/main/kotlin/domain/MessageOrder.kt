package domain

class MessageOrder(
    val correlationId: String,
    val payload: Order
) {
    fun continueWithCorrelationId(id: String): String = "${this.correlationId}, $id"
}
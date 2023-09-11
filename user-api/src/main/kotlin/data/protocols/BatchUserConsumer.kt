package data.protocols

interface BatchUserConsumer {
    fun run(consumer: (topics: List<String>) -> Unit)
}

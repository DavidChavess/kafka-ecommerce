import data.GenerateLocalReport
import infra.EmailKafkaProducer
import infra.UserKafkaConsumer

fun main() {
    GenerateLocalReport(UserKafkaConsumer(), EmailKafkaProducer()).run()
}
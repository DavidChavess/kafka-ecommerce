import data.FraudDetectorMessage
import infra.FraudDetectorKafkaConsumer
import infra.FraudDetectorKafkaProducer

fun main() {
    FraudDetectorMessage(FraudDetectorKafkaProducer(), FraudDetectorKafkaConsumer()).run()
}
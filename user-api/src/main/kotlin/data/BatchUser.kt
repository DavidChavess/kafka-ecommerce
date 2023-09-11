package data

import data.protocols.BatchUserConsumer
import data.protocols.UserDataBase
import data.protocols.UserProducer
import domain.usecase.BatchUserUseCase
import domain.usecase.ConsumerService

class BatchUser(
    private val batchUserConsumer: BatchUserConsumer,
    private val userDataBase: UserDataBase,
    private val userProducer: UserProducer
) : BatchUserUseCase, ConsumerService {

    override fun batch() {
        batchUserConsumer.run { topics ->
            topics
                .forEach { topic ->
                    userDataBase.findAll().forEach { user ->
                        userProducer.send(topic, user)
                    }
                }
        }
    }

    override fun run() {
        batch()
    }
}
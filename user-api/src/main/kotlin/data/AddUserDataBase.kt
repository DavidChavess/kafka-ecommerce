package data

import data.protocols.OrderConsumer
import data.protocols.UserDataBase
import domain.User
import domain.usecase.AddUserUseCase
import domain.usecase.ConsumerService

class AddUserDataBase(
    private val orderConsumer: OrderConsumer,
    private val userDataBase: UserDataBase,
) : AddUserUseCase, ConsumerService {

    override fun add() {
        orderConsumer.run {
            it
                .run { User(userId, name, email) }
                .run(userDataBase::add)
        }
    }

    override fun run() {
        add()
    }
}
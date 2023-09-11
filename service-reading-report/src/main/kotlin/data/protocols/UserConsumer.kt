package data.protocols

import domain.User

interface UserConsumer {
    fun run(consumer: (User) -> Unit )
}

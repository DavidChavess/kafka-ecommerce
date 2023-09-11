package data.protocols

import domain.User

interface UserProducer {
    fun send(topic: String, user: User)
}

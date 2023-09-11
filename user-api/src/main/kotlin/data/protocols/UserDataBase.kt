package data.protocols

import domain.User

interface UserDataBase {
    fun add(user: User)
    fun findAll(): List<User>
}

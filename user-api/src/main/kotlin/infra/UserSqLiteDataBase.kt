package infra

import data.protocols.UserDataBase
import domain.User
import java.sql.Connection
import java.util.UUID

class UserSqLiteDataBase(
    private var connection: Connection
) : UserDataBase {

    init {
        createDatabse()
    }

    override fun add(user: User) {
        println("adding new user")
        if (!existsByEmail(user.email)) {
            val statement = connection.prepareStatement("insert into Users (uuid, name, email) VALUES (?,?,?)");
            statement.setString(1, UUID.randomUUID().toString())
            statement.setString(2, user.name)
            statement.setString(3, user.email)
            statement.execute();
            println("User added!")
            return
        }
        println("User already exists")
    }

    override fun findAll(): List<User> {
        val statement = connection.prepareStatement("select * from Users");
        val resultSet = statement.executeQuery()
        val users = mutableListOf<User>()

        while (resultSet.next()) {
            val user = User(
                id = resultSet.getString("uuid"),
                name = resultSet.getString("name"),
                email = resultSet.getString("email")
            )
            users.add(user)
        }
        return users
    }

    private fun createDatabse() {
        connection.createStatement().execute(
            """create table if not exists Users (
                uuid varchar(255) primary key,
                name varchar(255),
                email varchar(255)
            )"""
        )
    }

    private fun existsByEmail(email: String): Boolean {
        println("verifying if new user already exists")
        val statement = connection.prepareStatement("select uuid from Users where email = ? limit 1");
        statement.setString(1, email)
        return statement.executeQuery().next();
    }
}

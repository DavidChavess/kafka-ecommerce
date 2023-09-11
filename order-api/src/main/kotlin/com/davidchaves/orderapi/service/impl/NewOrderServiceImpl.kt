package com.davidchaves.orderapi.service.impl

import com.davidchaves.orderapi.service.NewOrderService
import domain.Order
import org.springframework.stereotype.Service
import java.sql.Connection
import java.sql.DriverManager

@Service
class NewOrderServiceImpl(
    private var connection: Connection = DriverManager.getConnection("jdbc:sqlite:target/orders_database.db")
) : NewOrderService {

    init {
        createDatabse()
    }

    override fun wasProcessed(order: Order): Boolean {
        println("verifying if new user already exists")
        val statement = connection.prepareStatement("select uuid from Orders where uuid = ? limit 1");
        statement.setString(1, order.orderId)
        return statement.executeQuery().next();
    }

    override fun add(order: Order) {
        val statement = connection.prepareStatement("insert into Orders (uuid, name, email, total) VALUES (?,?,?,?)");
        statement.setString(1, order.orderId)
        statement.setString(2, order.name)
        statement.setString(3, order.email)
        statement.setString(4, order.total.toString())
        statement.execute();
        println("Order added!")
    }

    private fun createDatabse() {
        connection.createStatement().execute(
            """create table if not exists Orders (
                uuid varchar(255) primary key,
                name varchar(255),
                email varchar(255),
                total varchar(255)
            )"""
        )
    }
}

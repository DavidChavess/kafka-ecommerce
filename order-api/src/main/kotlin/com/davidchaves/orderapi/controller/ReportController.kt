package com.davidchaves.orderapi.controller

import com.davidchaves.orderapi.model.ProducerMessage
import com.davidchaves.orderapi.service.Producer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/report")
class ReportController(
    private val producer: Producer<List<String>>,
) {

    @GetMapping
    fun generateReports() {
        producer.send(
            "BATCH_USERS",
            ProducerMessage("GenerateReport(${UUID.randomUUID()})", listOf("USER_GENERATE_READING_REPORT"))
        )
    }
}
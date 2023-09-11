package data.protocols

import domain.User
import java.io.File

interface EmailProducer {
    fun send(user: User, report: File)
}

package data

import data.protocols.EmailProducer
import data.protocols.UserConsumer
import domain.GenerateReportUseCase
import util.Io
import java.io.File

class GenerateLocalReport(
    private val userConsumer: UserConsumer,
    private val emailProducer: EmailProducer
) : GenerateReportUseCase {

    override fun run() {
        userConsumer.run { user ->
            val uri = javaClass.classLoader.getResource("report.txt")?.toURI()
                ?: throw IllegalArgumentException("file not found!")
            val source = File(uri).toPath()
            val target = File(user.getReportPath())
            Io.copyTo(source, target)
            Io.append(target, user.id)
            Io.append(target, user.name)
            emailProducer.send(user, target)
            println("File created: ${target.absolutePath}")
        }
    }
}
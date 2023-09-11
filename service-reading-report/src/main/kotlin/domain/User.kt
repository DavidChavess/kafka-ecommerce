package domain

data class User(
    val id: String,
    val name: String,
    val email: String
) {
    fun getReportPath(): String = "target/$id-report.txt"
}

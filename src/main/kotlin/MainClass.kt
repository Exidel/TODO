import kotlinx.serialization.Serializable

enum class Complete { UNDONE, ACTIVE, DONE }


@Serializable
data class MainClass(
    val name: String = "",
    val id: Int = 0,
    val stage: Complete = Complete.UNDONE
)
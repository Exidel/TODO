import kotlinx.serialization.Serializable


@Serializable
data class MainClass(
    val name: String = "",
    val id: Int = 0,
    val stage: Stage = Stage.UNDONE
)
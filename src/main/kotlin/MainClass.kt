import kotlinx.serialization.Serializable


@Serializable
data class MainClass(
    val name: String = "",
    val id: Int = 0,
    val check: Boolean = false,
    val innerList: MutableList<MainClass> = mutableListOf()
) {

    fun addItem(nestedItem: MainClass) {
        innerList.add( nestedItem )
    }

}
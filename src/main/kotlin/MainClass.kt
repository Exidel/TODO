import kotlinx.serialization.Serializable


@Serializable
data class MainClass(
    var name: String = "",
    var check: Boolean = false,
    val innerList: MutableList<MainClass> = mutableListOf()
) {

    fun addItem(nestedItem: MainClass) {
        innerList.add( nestedItem )
    }

}
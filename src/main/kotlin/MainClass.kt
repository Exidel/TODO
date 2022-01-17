import kotlinx.serialization.Serializable


@Serializable
data class MainClass(
/** Basic properties */
    var name: String = "",
    var check: Boolean = false,
    val innerList: MutableList<MainClass> = mutableListOf(),
/** Feature properties */
    var test: Boolean = false
) {

    fun addItem(nestedItem: MainClass) {
        innerList.add( nestedItem )
    }

}
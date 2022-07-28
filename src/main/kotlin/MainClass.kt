import kotlinx.serialization.Serializable


@Serializable
data class MainClass(
/** Basic properties */
    var name: String = "",
    var check: Boolean = false,
    val innerList: MutableList<MainClass> = mutableListOf(),
/** Feature properties */
    /** Time */
    var addDate: String = "",
    var duration: Long = 0,
    /** Colors */
//    var bg: Color = Color.White
) {

    fun addItem(nestedItem: MainClass) {
        innerList.add( nestedItem )
    }

}
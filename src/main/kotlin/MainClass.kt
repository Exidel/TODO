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
    var bg: List<Float> = listOf(Colors.itemBG.red, Colors.itemBG.green, Colors.itemBG.blue, Colors.itemBG.alpha),
    var border: List<Float> = listOf(Colors.itemBorder.red, Colors.itemBorder.green, Colors.itemBorder.blue, Colors.itemBorder.alpha),
    var text: List<Float> = listOf(Colors.textColor.red, Colors.textColor.green, Colors.textColor.blue, Colors.textColor.alpha),
) {

    fun addItem(nestedItem: MainClass) {
        innerList.add( nestedItem )
    }

}
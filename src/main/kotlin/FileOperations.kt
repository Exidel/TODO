import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import java.io.File

object FileOperations {

    fun saveSettings(state: WindowState) {
        val settings: String =
            "${state.size.width.value.toInt()}" + "\n" + "${state.size.height.value.toInt()}" + "\n" +
                    "${state.position.x.value.toInt()}" + "\n" + "${state.position.y.value.toInt()}"
        if (state.placement == WindowPlacement.Floating) File("settings.txt").writeText(settings)
    }


    fun loadSettings(): Settings {

        val list = mutableListOf<String>()

        if (File("settings.txt").exists()) {
            try {
                val fileListStrings = File("settings.txt").readLines()
                if (fileListStrings.isNotEmpty()) {
                    list.addAll(fileListStrings)
                }
            } catch (e: Exception) { e.printStackTrace() }
        }

        val obj = if (list.isNotEmpty()) {
            Settings(
                size = if (list.size >= 2 && list[0] != "" && list[1] != "")
                    DpSize(list[0].toInt().dp, list[1].toInt().dp) else Settings().size,
                position = if (list.size >= 4 && list[2] != "" && list[3] != "")
                    WindowPosition(list[2].toInt().dp, list[3].toInt().dp) else Settings().position
            )
        } else Settings()

        return obj
    }

}
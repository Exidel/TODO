import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import java.io.File

object FileOperations {

    fun saveSettings(state: WindowState) {
        val settings: String =
            "${state.size.width.value.toInt()}" + "\n" + "${state.size.height.value.toInt()}" + "\n" +
                    "${state.position.x.value.toInt()}" + "\n" + "${state.position.y.value.toInt()}"
        File("settings.txt").writeText(settings)
    }


    fun loadSettings(): Settings {

        var obj = Settings()

        if (File("settings.txt").exists()) {
            try {
                val list = File("settings.txt").readLines()
                if (list.isNotEmpty()) {
                    obj = Settings(
                        size = if (list.size >= 2) DpSize(list[0].toInt().dp, list[1].toInt().dp) else Settings().size,
                        position = if (list.size >= 4) WindowPosition(list[2].toInt().dp, list[3].toInt().dp) else Settings().position
                    )
                }
            } catch (e: Exception) { e.printStackTrace() }
        }

        return obj
    }

}
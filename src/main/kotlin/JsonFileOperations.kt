import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import kotlin.io.path.Path


object JsonFileOperations {

    fun createJsonFromList(mainList: SnapshotStateList<MainClass>) {
        val jsonFormat = Json { prettyPrint = true }
        val string: String = if (mainList.size > 0) jsonFormat.encodeToString(mainList.toList()) else ""
        val path = Path("data")
        if (Files.notExists(path)) Files.createDirectory(path)
        val file = FileWriter("data\\database.json", Charsets.UTF_8)
        if (string != "") {
            file.write(string)
            file.flush()
            file.close()
        } else println("createJsonFromList() save fail, string is empty")
    }

    fun parseJsonToObjects(): SnapshotStateList<MainClass> {
        val mainList: SnapshotStateList<MainClass> = mutableStateListOf()
        val path = Path("data\\database.json")
        if (Files.exists(path)) {
            val jsonContent = File(path.toString()).readText(charset = Charsets.UTF_8)
            if (jsonContent != "") {
                val obj = Json.decodeFromString<List<MainClass>>(jsonContent)
                mainList.addAll(obj)
            } else println("parseJsonToObjects() load fail, file is empty")
        }
        return mainList
    }

}


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
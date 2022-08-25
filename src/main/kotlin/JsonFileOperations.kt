import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import task_features.color_picker.ColorPalette
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

    fun createColorPalette(list: List<ColorPalette>) {
        val jsonFormat = Json { prettyPrint = true }
        val string = if (list.isNotEmpty()) jsonFormat.encodeToString(list) else ""
        val path = Path("data")
        if (Files.notExists(path)) Files.createDirectory(path)
        File("data/color_palette.json").writeText(string, Charsets.UTF_8)

    }

    fun loadColorPalette(): SnapshotStateList<ColorPalette> {
        val list = mutableStateListOf<ColorPalette>()
        val path = Path("data/color_palette.json")
        if (Files.exists(path)) {
            val jsonContent = File(path.toString()).readText(Charsets.UTF_8)
            if (jsonContent.isNotEmpty()) {
                val obj = Json.decodeFromString<List<ColorPalette>>(jsonContent)
                list.addAll(obj)
            }
        }

        return list
    }

}
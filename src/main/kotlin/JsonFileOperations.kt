import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import kotlin.io.path.Path


class JsonFileOperations {

    fun createJsonFromList(mainList: SnapshotStateList<MainClass>) {
        val jsFormat = Json { prettyPrint = true; encodeDefaults = true }
        val string: String = if (mainList.size > 0) jsFormat.encodeToString(mainList.toList()) else ""
        val path = Path("data")
        if (Files.notExists(path)) Files.createDirectory(path)
        val file = FileWriter("data\\database.json", Charsets.UTF_8)
        if (string != "") {
            file.write(string)
            file.flush()
            file.close()
        } else println("fail")
    }

    fun parseJsonToObjects(): SnapshotStateList<MainClass> {
        val mainList: SnapshotStateList<MainClass> = mutableStateListOf()
        val path = Path("data\\database.json")
        if (Files.exists(path)) {
            val jsonContent = File(path.toString()).readText(charset = Charsets.UTF_8)
            val obj = Json.decodeFromString<List<MainClass>>(jsonContent)
            mainList.addAll(obj)
        }
        return mainList
    }

}
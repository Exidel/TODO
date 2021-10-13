import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {

    val mainList = JsonFileOperations().parseJsonToObjects()
    val mainWindow = rememberWindowState( size = DpSize(350.dp, 500.dp), position = WindowPosition(Alignment.Center) )

    var screenState by remember { mutableStateOf(false) }
    var index by remember { mutableStateOf(0) }

    Window(state = mainWindow, onCloseRequest = ::exitApplication, icon = null) {

        Box(Modifier.fillMaxSize()) {
            if (!screenState) MainView(mainList, {index = it}, {screenState = it})
            else DescriptionScreen(mainList, {screenState = it}, index)
        }

    }

}
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

    var text by remember { mutableStateOf("GitHub newbie test") }
    val mainWindow = rememberWindowState( size = DpSize(500.dp, 300.dp), position = WindowPosition(Alignment.Center) )

    Window(state = mainWindow, onCloseRequest = ::exitApplication, icon = null) {

        Box(Modifier.fillMaxSize()) {
            Text(text)
        }

    }

}
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState


fun main() = application {

    val mainList = JsonFileOperations().parseJsonToObjects()
    val mainWindow = rememberWindowState( size = DpSize(360.dp, 500.dp), position = WindowPosition(Alignment.Center) )
    var index by remember { mutableStateOf(0) }
    var description by remember { mutableStateOf(false) }
    val title = remember { mutableStateOf(if (mainList.isNotEmpty()) mainList[index].name else "") }


    Window(state = mainWindow, onCloseRequest = ::exitApplication, title = "TODO", icon = null, undecorated = false, resizable = false) {

        Box(Modifier.fillMaxSize().background( Color(80, 80, 80, 255) ) ) {

            WindowDraggableArea {
                Box(Modifier.align(Alignment.TopStart).fillMaxWidth().height(30.dp).background(Color.Gray)) {
                    Image(painter = painterResource("logo.png"), contentDescription = null, modifier = Modifier.align(Alignment.Center))
                }
            }

            MainView(mainList, index, {index = it}, { description = true }, {title.value = it})

            DescriptionScreen( mainList, { description = it }, index, title.value )

        }

        if (description) mainWindow.size = mainWindow.size.copy(width = 800.dp)
        else mainWindow.size = mainWindow.size.copy(width = 360.dp)

    }

}
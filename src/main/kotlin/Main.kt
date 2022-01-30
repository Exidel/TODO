import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState


@OptIn(ExperimentalMaterialApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
fun main() = application {

    val mainList = JsonFileOperations().parseJsonToObjects()
    val mainWindow = rememberWindowState(size = DpSize(400.dp, 500.dp), position = WindowPosition(Alignment.Center))
    var index by remember { mutableStateOf(0) }
    var description by remember { mutableStateOf(false) }
    val title = remember { mutableStateOf(if (mainList.isNotEmpty()) mainList[index].name else "") }


CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {

    Window( state = mainWindow, onCloseRequest = ::exitApplication, undecorated = true ) {

        Box(Modifier.fillMaxSize().background(Color(80, 80, 80, 255))) {

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                WindowDraggableArea { ApplicationTopBar(mainWindow) { exitApplication() } }

                if (!description) {
                    MainView(mainList, index, { index = it }, { description = true }, { title.value = it })
                } else {
                    DescriptionScreen(mainList, { description = it }, index, title.value)
                }

            }

        }

    }
}


}
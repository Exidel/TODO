import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainView(mainList: SnapshotStateList<MainClass>) {

    var tfState by remember { mutableStateOf("") }
    val columnScroll = rememberScrollState(0)

    Box(Modifier.fillMaxSize()){
        Box{
            Column(Modifier
                .padding(10.dp, 10.dp, 10.dp, 80.dp)
                .width(300.dp)
                .fillMaxHeight()
                .align(Alignment.TopStart)
                .verticalScroll(columnScroll)
            ) {
                if (mainList.size > 0) {
                    for (i in mainList.indices) {
                        TaskListItems("#${mainList[i].id}" + " " + mainList[i].name)
                    }
                }
            }

            VerticalScrollbar(
                adapter = ScrollbarAdapter(columnScroll),
                modifier = Modifier.padding(start = 2.dp, top = 13.dp, end = 2.dp, bottom = 83.dp).align(Alignment.TopEnd)
                    .border(2.dp, Color.DarkGray)
            )
        }

        Row(Modifier.padding(5.dp).align(Alignment.BottomStart).fillMaxWidth()) {

            OutlinedTextField(
                value = tfState,
                onValueChange = { tfState = it },
                singleLine = true,
                label = { Text("Enter new task") },
                keyboardActions = KeyboardActions(),
                modifier = Modifier.onKeyEvent {
                    if (it.key == Key.Enter && it.type == KeyEventType.KeyUp) {
                        if (tfState != "") {
                            mainList.add( MainClass(name = tfState, id = if (mainList.isEmpty()) 1 else mainList.lastIndex+1) )
                            tfState = ""
                        }
                        true
                    } else {false}
                }
            )

            IconButton(onClick = {},
                modifier = Modifier.size(48.dp, 48.dp).align(Alignment.CenterVertically)
            ) { Icon(Icons.Rounded.AddCircle, null) }

        }
    }



}
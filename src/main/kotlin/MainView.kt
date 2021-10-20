import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainView(mainList: SnapshotStateList<MainClass>, index: (Int) -> Unit, winSize: () -> Unit) {

    var tfState by remember { mutableStateOf("") }
    val columnScroll = rememberScrollState(0)

    Box(Modifier.fillMaxSize()){
        Box(Modifier.width(330.dp)){
            Column(Modifier
                .padding(10.dp, 25.dp, 10.dp, 80.dp)
                .width(300.dp)
                .fillMaxHeight()
                .align(Alignment.TopStart)
                .verticalScroll(columnScroll)
            ) {
                if (mainList.size > 0) {
                    for (i in mainList.indices) {
                        TaskListItems("#${mainList[i].id}" + " " + mainList[i].name) {
                            index(i)
                            winSize.invoke()
                        }
                    }
                }
            }

            VerticalScrollbar(
                adapter = ScrollbarAdapter(columnScroll),
                modifier = Modifier
                    .padding(start = 2.dp, top = 28.dp, end = 2.dp, bottom = 83.dp)
                    .align(Alignment.TopEnd),
                style = ScrollbarStyle(
                    minimalHeight = 16.dp,
                    thickness = 7.dp,
                    shape = RoundedCornerShape(4.dp),
                    hoverDurationMillis = 200,
                    unhoverColor = Color.White,
                    hoverColor = Color.Gray
                )
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
                            mainList.add( MainClass(name = tfState, id = mainList.size+1) )
                            tfState = ""
                            JsonFileOperations().createJsonFromList(mainList)
                        }
                        true
                    } else {false}
                }
            )

            IconButton(onClick = {},
                modifier = Modifier.size(48.dp, 48.dp).align(Alignment.CenterVertically)
            ) { Icon(painter = painterResource("baseline_mic_black_24dp.png"), null, tint = Color.White) }

        }
    }



}
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


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
                if (mainList.isNotEmpty()) {
                    for (i in mainList.indices) {
                        TaskListElement(
                            text = "#${i + 1}" + " " + mainList[i].name,
                            winSize = {
                                index(i)
                                winSize.invoke()
                            },
                            edit = {  },
                            delete = { mainList.removeAt(i); JsonFileOperations().createJsonFromList(mainList) }
                        )
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

        Row(Modifier.padding(5.dp).align(Alignment.BottomStart).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            MainScreenTF(tfState, {tfState = it}) {
                if (tfState != "") {
                    mainList.add( MainClass(tfState) )
                    tfState = ""
                    JsonFileOperations().createJsonFromList(mainList)
                }
            }  /** Add TextField */

            AddIcon {
                if (tfState != "") {
                    mainList.add( MainClass(tfState) )
                    tfState = ""
                    JsonFileOperations().createJsonFromList(mainList)
                }
            }  /** Add Icon */

        }
    }



}
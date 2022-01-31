import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MainView(mainList: SnapshotStateList<MainClass>, index: Int, indexChange: (Int) -> Unit, winSize: () -> Unit, title: (String) -> Unit) {

    var tfState by remember { mutableStateOf("") }
    val columnScroll = rememberScrollState(0)


    Box {

        Box( Modifier.padding(start = 20.dp, bottom = 80.dp).width(342.dp) ) {

            Column(Modifier
                    .fillMaxSize()
                    .align(Alignment.TopStart)
                    .verticalScroll(columnScroll)
            ) {

                if (mainList.isNotEmpty()) {
                    for (i in mainList.indices) {

                        TaskElement(
                            num = "#${i + 1} ",
                            item = mainList[i],
                            indexChange = { indexChange(i) },
                            winSize = { indexChange(i); winSize.invoke() },
                            delete = { if (i>0) indexChange(i-1) else indexChange(0); mainList.removeAt(i) },
                            save = { JsonFileOperations().createJsonFromList(mainList) },
                            title = title
                        )

                    }
                }

            }

            VerticalScrollbar(
                adapter = ScrollbarAdapter(columnScroll),
                modifier = Modifier
                    .padding(3.dp)
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

/** Add TF + Icon */
        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 5.dp, end = 5.dp, bottom = 10.dp)
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            MainScreenTF(tfState, {tfState = it}) {
                if (tfState != "") {
                    mainList.add( MainClass(tfState) )
                    tfState = ""
                    JsonFileOperations().createJsonFromList(mainList)
                }
            }

            IconPreset(Icons.Rounded.Send, width = 48, height = 48) {
                if (tfState != "") {
                    mainList.add( MainClass(tfState) )
                    tfState = ""
                    JsonFileOperations().createJsonFromList(mainList)
                }
            }

        }

    }


}
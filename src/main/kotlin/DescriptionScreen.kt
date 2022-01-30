import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DescriptionScreen(
        mainList: SnapshotStateList<MainClass>,
        closeDescription: (Boolean) -> Unit,
        index: Int,
        title: String
) {

    val verticalScroll = rememberScrollState(0)
    val horizontalScroll = rememberScrollState(0)
    var addState by remember { mutableStateOf(false) }
    var tfText by remember { mutableStateOf("") }
    var trigger by remember { mutableStateOf(mainList, neverEqualPolicy()) }

    LaunchedEffect(mainList) { trigger = mainList }


    Box( Modifier.fillMaxSize() ) {

/** close icon */
        Box(Modifier.padding(start = 15.dp, top = 5.dp).align(Alignment.TopStart)) {
            IconButton(onClick = { closeDescription(false) }) {Icon(painterResource("round_rollback_black_48dp.png"), null, Modifier.size(24.dp), Color.White)}
        }

        if (mainList.isNotEmpty()) {

            Column {

/** Task Title */
                Text(
                    text = if (mainList[index].name == title) title else mainList[index].name,
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 25.dp, end = 20.dp, bottom = 20.dp)
                        .align(Alignment.CenterHorizontally)
                )

/** SubTask column + scrollbars */
                Box {

                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 30.dp)
                            .verticalScroll(verticalScroll)
                            .horizontalScroll(horizontalScroll),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        if ( mainList.isNotEmpty() && mainList[index].innerList.isNotEmpty() ) {
                            AddSubTask(trigger[index].innerList, { trigger = trigger }) { JsonFileOperations().createJsonFromList(mainList) }
                        }

                        if (!addState) {
                            AddBox { addState = true }
                        } else {
                            AddEditTF(tfText, {tfText = it}, { addState = false; tfText = "" }) {
                                if (tfText != "") {
                                    addState = false
                                    mainList[index].addItem( MainClass(tfText) )
                                    tfText = ""
                                    JsonFileOperations().createJsonFromList(mainList)
                                }
                            }
                        }  /** Box + */

                    }

                    VerticalScrollbar(
                        adapter = ScrollbarAdapter(verticalScroll),
                        modifier = Modifier
                            .padding(start = 2.dp, top = 5.dp, end = 10.dp, bottom = 35.dp)
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

                    HorizontalScrollbar(
                        adapter = ScrollbarAdapter(horizontalScroll),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 15.dp)
                            .align(Alignment.BottomStart),
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

            }

        }

    }

}






/** Recursive function */
@Composable
fun AddSubTask(
    list: MutableList<MainClass>,
    trigger: () -> Unit,
    save: () -> Unit
) {


    if (list.isNotEmpty()) {
        for (item in list) {


            SubTaskElement(
                item = item,
                save = save,
                trigger = trigger,
                delete = { list.remove(item); save.invoke(); trigger.invoke() }
            ) {
                AddSubTask(item.innerList, trigger, save)
            }


        }
    }

}
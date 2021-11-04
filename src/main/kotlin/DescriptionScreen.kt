import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionScreen(mainList: SnapshotStateList<MainClass>, closeDescription: (Boolean) -> Unit, index: Int) {

    val verticalScroll = rememberScrollState(0)
    val horizontalScroll = rememberScrollState(0)
    var addState by remember { mutableStateOf(false) }
    var tfText by remember { mutableStateOf("") }

    Box( Modifier.padding(start = 360.dp, top = 20.dp).fillMaxSize() ) {

        Box(Modifier.padding(end = 5.dp, top = 5.dp).align(Alignment.TopEnd)) {
            IconPreset(Icons.Rounded.Close) { closeDescription(false) }
        }  /** close icon */

        Box {

            Text(
                text = if (mainList.size > 0) mainList[index].name else "",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .align(Alignment.TopCenter)
            ) /** Task Title */

            Column(
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 30.dp)
                    .verticalScroll(verticalScroll)
                    .horizontalScroll(horizontalScroll)
                    .padding(start = 10.dp)
            ) {

                if ( mainList.isNotEmpty() && mainList[index].innerList.isNotEmpty() ) {
                    AddSubTask(mainList[index].innerList) { JsonFileOperations().createJsonFromList(mainList) }
                }

                if (!addState) {
                    AddBox { addState = true }
                } else {
                    AddEditTF(tfText, {tfText = it}, { addState = false; tfText = "" }) {
                        addState = false
                        mainList[index].addItem( MainClass(tfText) )
                        tfText = ""
                        JsonFileOperations().createJsonFromList(mainList)
                    }
                }

            }

            VerticalScrollbar(
                adapter = ScrollbarAdapter(verticalScroll),
                modifier = Modifier
                    .padding(start = 2.dp, top = 60.dp, end = 10.dp, bottom = 35.dp)
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
                    .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 15.dp)
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






/** Recursive function */
@Composable
fun AddSubTask(
    list: MutableList<MainClass>,
    save: () -> Unit
) {


    if (list.isNotEmpty()) {
        for (item in list) {


            SubTaskElement(
                item = item,
                save = save,
                delete = { list.remove(item); save.invoke() }
            ) {
                AddSubTask(item.innerList, save)
            }


        }
    }

}
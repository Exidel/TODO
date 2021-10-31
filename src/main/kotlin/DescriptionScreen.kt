import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionScreen(mainList: SnapshotStateList<MainClass>, closeDescription: (Boolean) -> Unit, index: Int) {


    Box( Modifier.padding(start = 360.dp, top = 20.dp).fillMaxSize() ) {

        Column(Modifier.fillMaxWidth()) {

            Text(
                text = if (mainList.size > 0) mainList[index].name else "",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 15.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Box {

                SubTaskElement(
                    name = mainList[index].name,
                    checkState = mainList[index].check,
                    add = { mainList[index].addItem(MainClass("sub1")) },
                    edit = {  },
                    delete = {  }
                ) {

                    if ( mainList[index].innerList.isNotEmpty() ) {
                        AddSubTask(mainList[index].innerList, mainList)
                    }

                }

            }

        }

        Box(Modifier.padding(end = 5.dp, top = 5.dp).align(Alignment.TopEnd)) {
            IconPreset(Icons.Rounded.Close) { closeDescription(false) }
        }

    }

}







@Composable
fun AddSubTask(list: MutableList<MainClass>, mainList: SnapshotStateList<MainClass>) {

    val save = JsonFileOperations().createJsonFromList(mainList)

    if (list.isNotEmpty()) {
        for (item in list) {
            SubTaskElement(
                name = item.name,
                checkState = item.check,
                add = { item.addItem(MainClass("subsub1")); save },
                edit = {  },
                delete = {list.remove(item); save}
            ) {
                AddSubTask(item.innerList, mainList)
            }
        }
    }

}
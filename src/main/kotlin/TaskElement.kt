import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TaskElement(
        num: String,
        item: MainClass,
        indexChange: () -> Unit,
        winSize: () -> Unit,
        delete: () -> Unit,
        save: () -> Unit,
        title: (String) -> Unit
) {

    var dragX by remember { mutableStateOf(0f) }
    var tfText by remember { mutableStateOf("")}
    var editTF by remember { mutableStateOf(false) }

    Box(Modifier.padding(2.dp, 2.dp, 20.dp, 2.dp).width(300.dp)) {


        if (editTF) {

            AddEditTF(item.name, {tfText = it}, { editTF = false; tfText = "" }) {
                if (tfText != "") {
                    item.name = tfText
                    title(tfText)
                    tfText = ""
                    editTF = false
                    save.invoke()
                }
            }

        } else  {

            Row(Modifier.padding(start = 10.dp).align(Alignment.CenterStart), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                TooltipPreset(Labels.edit) { IconPreset(Icons.Rounded.Edit) { editTF = true; indexChange.invoke(); title(item.name) } }

                TooltipPreset(Labels.delete) { IconPreset(Icons.Rounded.Delete) { dragX = 0f; delete.invoke(); save.invoke() } }

            } /** Left icons */


            Box(
                Modifier
                    .offset { IntOffset(dragX.toInt(), 0) }
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta -> if (dragX in (-10f..70F)) dragX += delta },
                        onDragStopped = { dragX = if (dragX > 30) 60F else 0f }
                    )
            ) {

                Text(
                    text = num + item.name,
                    fontSize = 13.sp,
                    color = Colors.textColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart)
                        .background(color = Colors.itemBG, shape = RoundedCornerShape(12.dp))
                        .border(width = 1.dp, color = Colors.itemBorder, shape = RoundedCornerShape(12.dp))
                        .clip(shape = RoundedCornerShape(12.dp))
                        .clickable { winSize.invoke(); title(item.name) }
                        .padding(start = 10.dp, 3.dp, 5.dp, 3.dp)
                )
            } /** Draggable element */

        }



    }

}
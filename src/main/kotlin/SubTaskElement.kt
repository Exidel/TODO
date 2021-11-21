import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SubTaskElement(
    item: MainClass,
    delete: () -> Unit,
    save: () -> Unit,
    spoilerContent: @Composable () -> Unit
) {

    var dragX by remember { mutableStateOf(0f) }
    var expand by remember { mutableStateOf(false) }
    var check by remember { mutableStateOf(item.check) }
    var tfText by remember { mutableStateOf("") }
    var addTF by remember { mutableStateOf(false) }
    var editTF by remember { mutableStateOf(false) }


    Box {

        Column {

            Box(Modifier.padding(bottom = 2.dp).width(400.dp)) {

                if (addTF && !editTF) {

                    AddEditTF(tfText, {tfText = it}, { addTF = false }) {
                        if (tfText != "") {
                            item.addItem( MainClass(tfText) )
                            tfText = ""
                            save.invoke()
                            addTF = false
                        }
                    }

                } else if (editTF && !addTF) {

                    AddEditTF(item.name, {tfText = it}, { editTF = false; tfText = "" }) {
                        if (tfText != "") {
                            item.name = tfText
                            tfText = ""
                            save.invoke()
                            editTF = false
                        }
                    }

                } else {

                    Row(Modifier.padding(start = 10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                        TooltipPreset("Add subtask") { IconPreset(Icons.Rounded.Add) { addTF = true; editTF = false } }

                        TooltipPreset("Edit") { IconPreset(Icons.Rounded.Edit) { editTF = true; addTF = false; tfText += item.name } }

                        TooltipPreset("Delete") { IconPreset(Icons.Rounded.Delete) { delete.invoke() } }

                    }  /** left icons */

                    Row(Modifier.padding(end = 40.dp).align(Alignment.CenterEnd), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

//                        Box(
//                            Modifier
//                                .size(16.dp, 16.dp)
//                                .background( Brush.sweepGradient( listOf(Color.Cyan, Color.Red, Color.Yellow, Color.Green) ) )
//                                .clickable {  }
//                        )  /** color picker */

                        TooltipPreset("Settings") { IconPreset(Icons.Rounded.Settings) {} }

                        Text(
                            text = "Txt",
                            textDecoration = TextDecoration.LineThrough,
                            color = Color.White,
                            modifier = Modifier.padding(start = 3.dp).clickable { check = !check; item.check = check; save.invoke() }
                        )

                    }  /** right icons */

                    Box(
                        modifier = Modifier
                            .offset { IntOffset(dragX.toInt(), 0) }
                            .draggable(
                                orientation = Orientation.Horizontal,
                                state = rememberDraggableState { delta -> if (dragX in -10f..100F) dragX += delta },
                                onDragStopped = { dragX = if (dragX > 45) 90F else 0f }
                            )
                    ) {

                        Text(
                            text = if (item.name != "") item.name else "",
                            fontSize = 13.sp,
                            textDecoration = if (check) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier
                                .width(300.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                                .clip(shape = RoundedCornerShape(12.dp))
                                .clickable { expand = !expand }
                                .padding(start = 10.dp, 3.dp, 3.dp, 3.dp)
                        )

                    }  /** draggable element */

                }

            }

            AnimatedVisibility(expand) {
                Column(Modifier.padding(start = 15.dp)) {
                    spoilerContent.invoke()
                }
            }
        }
    }

}




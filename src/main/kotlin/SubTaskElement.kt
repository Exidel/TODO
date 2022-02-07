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
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.rememberCursorPositionProvider
import task_features.IconsBox
import task_features.TimeEvents
import task_features.TimeEventsScreen


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SubTaskElement(
    item: MainClass,
    delete: () -> Unit,
    save: () -> Unit,
    trigger: () -> Unit,
    spoilerContent: @Composable () -> Unit
) {

    var dragX by remember { mutableStateOf(0f) }
    var expand by remember { mutableStateOf(false) }
    var tfText by remember { mutableStateOf("") }
    var addTF by remember { mutableStateOf(false) }
    var editTF by remember { mutableStateOf(false) }
    var check by remember { mutableStateOf(item.check, neverEqualPolicy()) }
var popup by remember { mutableStateOf(false) }

    LaunchedEffect(item) { check = item.check }

    Box {

        Column {

            Box(Modifier.width(400.dp)) {

                if (addTF && !editTF) {

                    AddEditTF(tfText, {tfText = it}, { addTF = false }) {
                        if (tfText != "") {
                            item.addItem( MainClass(tfText, addDate = TimeEvents().taskAddTime()) )
                            tfText = ""
                            save.invoke()
                            addTF = false
                            trigger.invoke()
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

/** left icons */
                    Row(Modifier.padding(start = 10.dp).align(Alignment.CenterStart), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                        TooltipPreset("Add subtask") { IconPreset(Icons.Rounded.Add) { addTF = true; editTF = false } }

                        TooltipPreset("Edit") { IconPreset(Icons.Rounded.Edit) { editTF = true; addTF = false; tfText += item.name } }

                        TooltipPreset("Delete") { IconPreset(Icons.Rounded.Delete) { dragX = 0f; delete.invoke() } }

                    }

/** right icons */
                    Row(Modifier.padding(end = 40.dp).align(Alignment.CenterEnd), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                        TooltipPreset("Settings") { IconPreset(Icons.Rounded.Settings) { popup = true } }

                        Text(
                            text = "Tt",
                            textDecoration = TextDecoration.LineThrough,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .clickable { check = !check; item.check = check; save.invoke() }
                        )

                    }

/** draggable element */
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

                    }

                }

            }

            AnimatedVisibility(expand) {
                Column(Modifier.padding(start = 30.dp, top = 4.dp, bottom = 4.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    spoilerContent.invoke()
                }
            }

        }
//        if (popup) Popup(onDismissRequest = {popup = false}, popupPositionProvider = rememberCursorPositionProvider(), focusable = true) { IconsBox(item) }
        if (popup) Popup(onDismissRequest = {popup = false}, popupPositionProvider = rememberCursorPositionProvider(), focusable = true) { TimeEventsScreen({popup = false}) }
    }

}




import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SubTaskElement(
    name: String,
    checkState: Boolean,
    add: () -> Unit,
    edit: () -> Unit,
    delete: () -> Unit,
    spoilerContent: @Composable () -> Unit
) {

    var dragX by remember { mutableStateOf(0f) }
    var expand by remember { mutableStateOf(false) }
    var check by remember { mutableStateOf(checkState) }


    Box {

        Column {

            Box(Modifier.width(400.dp)) {

                Row(Modifier.padding(start = 10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                    IconPreset(Icons.Rounded.Add) { add.invoke() }

                    IconPreset(Icons.Rounded.Edit) { edit.invoke() }

                    IconPreset(Icons.Rounded.Delete) { delete.invoke() }

                }

                Row(Modifier.padding(end = 10.dp).align(Alignment.CenterEnd), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                    Box(
                        Modifier
                            .size(16.dp, 16.dp)
                            .background( Brush.sweepGradient( listOf(Color.Cyan, Color.Red, Color.Yellow, Color.Green) ) )
                            .clickable {  }
                    )

                    Row {
                        Checkbox(check, {check = it}, modifier = Modifier.size(16.dp, 16.dp))
                        Text("A a", textDecoration = TextDecoration.LineThrough, color = Color.White, modifier = Modifier.padding(start = 3.dp))
                    }

                }

                Box(
                    modifier = Modifier
                        .offset { IntOffset(dragX.toInt(), 0) }
                        .draggable(
                            orientation = Orientation.Horizontal,
                            state = rememberDraggableState { delta -> if (dragX in -10f..100F) dragX += delta },
                            onDragStopped = { dragX = if (dragX > 0) 90F else 0f }
                        )
                        .clickable { expand = !expand }
                ) {

                    Text(
                        text = if (name != "") name else "",
                        fontSize = 13.sp,
                        textDecoration = if (check) TextDecoration.LineThrough else TextDecoration.None,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp)
                            .width(300.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                            .padding(start = 10.dp, 3.dp, 3.dp, 3.dp)
                    )

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
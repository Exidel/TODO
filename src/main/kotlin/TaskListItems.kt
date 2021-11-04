import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskListElement(
        text: String,
        winSize: () -> Unit,
        edit: () -> Unit,
        delete: () -> Unit
) {

    var dragX by remember { mutableStateOf(0f) }

    Box(Modifier.padding(2.dp).fillMaxWidth()) {

        Row(Modifier.padding(start = 10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            TooltipPreset("Rename") { IconPreset(Icons.Rounded.Edit) { edit.invoke() } }

            TooltipPreset("Delete") { IconPreset(Icons.Rounded.Delete) { delete.invoke() } }

        }

        Box(Modifier.align(Alignment.CenterEnd)) {
            IconPreset(Icons.Rounded.PlayArrow) {  }
        }

        Box(
            Modifier
                .offset { IntOffset(dragX.toInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta -> if (dragX in -10f..70F) dragX += delta },
                    onDragStopped = { dragX = if (dragX > 30) 60F else 0f }
                )
        ) {
            Text(
                text = text,
                fontSize = 13.sp,
                modifier = Modifier
                    .padding(end = 22.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                    .clip(shape = RoundedCornerShape(12.dp))
                    .clickable { winSize.invoke() }
                    .padding(start = 10.dp, 3.dp, 3.dp, 3.dp)
            )
        }



    }

}
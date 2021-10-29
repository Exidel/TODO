import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.CoroutineScope
import java.lang.reflect.Proxy
import kotlin.math.roundToInt

@Composable
fun DescriptionScreen(mainList: SnapshotStateList<MainClass>, closeDescription: (Boolean) -> Unit, index: Int) {

    var expand by remember { mutableStateOf(false) }

    Box( Modifier.padding(start = 360.dp, top = 20.dp).fillMaxSize() ) {

        Column(Modifier.fillMaxWidth()) {

            Text("${mainList[index].name}", fontSize = 30.sp, color = Color.White,
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp).align(Alignment.CenterHorizontally) )

            Box {
                ColumnTask(mainList, index)
            }

        }

    }

}



@Composable
fun DescriptionTaskElement(
    mainList: SnapshotStateList<MainClass>,
    index: Int,
//    expand: () -> Unit
) {

    var dragX by remember { mutableStateOf(0f) }
    var check by remember { mutableStateOf(false) }
    var expand by remember { mutableStateOf(false) }

    Box(Modifier.width(400.dp)) {

        Row(Modifier.padding(start = 10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            IconButton(onClick = {}, modifier = Modifier.size(19.dp, 19.dp)) {
                Icon(imageVector = Icons.Rounded.Delete, null, tint = Color.White)
            }

            IconButton(onClick = {}, modifier = Modifier.size(19.dp, 19.dp)) {
                Icon(imageVector = Icons.Rounded.Edit, null, tint = Color.White)
            }

        }

        Row(Modifier.padding(end = 10.dp).align(Alignment.CenterEnd), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            IconButton(onClick = {}, modifier = Modifier.size(20.dp, 20.dp)) {
                Icon(imageVector = Icons.Rounded.ExitToApp, null, tint = Color.White)
            }

            Row {
                Checkbox(check, {check = it}, modifier = Modifier.padding(end = 3.dp).size(20.dp, 20.dp))
                Text("A a", textDecoration = TextDecoration.LineThrough, color = Color.White)
            }

        }

        Box(
            modifier = Modifier
                .offset { IntOffset(dragX.toInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta -> if (dragX in -10f..70F) dragX += delta },
                    onDragStopped = { dragX = if (dragX > 0) 60F else 0f }
                )
        ) {

            Text(
                text = mainList[index].name,
                fontSize = 13.sp,
                textDecoration = if (check) TextDecoration.LineThrough else TextDecoration.None,
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .width(300.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                    .clickable { expand = !expand }
                    .padding(start = 10.dp, 3.dp, 3.dp, 3.dp)
            )

        }

    }

}



@Composable
fun ColumnTask(mainList: SnapshotStateList<MainClass>, index: Int) {

    var expand by remember { mutableStateOf(false) }

    Column {
        DescriptionTaskElement(mainList, index)

        AnimatedVisibility(expand, modifier = Modifier.padding(start = 15.dp)) {
            DescriptionTaskElement(mainList, index)
        }
    }

}
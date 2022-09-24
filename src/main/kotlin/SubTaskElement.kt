import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.rememberCursorPositionProvider
import kotlinx.coroutines.delay
import task_features.IconsBox
import task_features.TimeEvents


@OptIn(ExperimentalAnimationApi::class)
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
    var showErrorMessage by remember { mutableStateOf(false) }

/** Item colors */
    val bg = Color(item.bg[0], item.bg[1], item.bg[2], item.bg[3])
    val border = Color(item.border[0], item.border[1], item.border[2], item.border[3])
    val text = Color(item.text[0], item.text[1], item.text[2], item.text[3])

    LaunchedEffect(item) { check = item.check }
    LaunchedEffect(showErrorMessage) { delay(2000); showErrorMessage = false }

    if (item.check && !checkDescendantOnDone(item.innerList)) LaunchedEffect(Unit) {
        check = !check
        item.check = check
        expand = false
        trigger.invoke()
    }


    AnimatedVisibility(
        visible = showErrorMessage,
        enter = fadeIn() + slideInVertically() + scaleIn() + expandVertically(),
        exit = fadeOut() + slideOutVertically() + shrinkVertically()
    ) {
        Text(
            text = Labels.errorMessage,
            color = Color.White,
            modifier = Modifier
                .background(Colors.bg, RoundedCornerShape(12.dp))
                .border(1.dp, Color(0.8f, 0.8f, 0.8f), RoundedCornerShape(12.dp))
                .padding(20.dp, 5.dp)
        )
    }


    Box {

        Column {

            Box(Modifier.width(400.dp)) {

                if (editTF && !addTF) {

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

                        TooltipPreset(Labels.edit) {
                            IconPreset(Icons.Rounded.Edit, mod = Modifier
                                .size(19.dp)
                                .background( Colors.bg, RoundedCornerShape(5.dp) )
                                .padding(1.dp)
                            ) { editTF = true; addTF = false; tfText += item.name }
                        }

                        TooltipPreset(Labels.delete) {
                            IconPreset(Icons.Rounded.Delete, mod = Modifier
                                .size(19.dp)
                                .background( Colors.bg, RoundedCornerShape(5.dp) )
                                .padding(1.dp)
                            ) { dragX = 0f; delete.invoke() }
                        }

                    }

/** right icons */
                    Row(Modifier.padding(end = 40.dp).align(Alignment.CenterEnd), horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                        TooltipPreset(Labels.settings) { IconPreset(Icons.Rounded.Settings) { popup = true } }

                        Text(
                            text = Labels.crossedText,
                            textDecoration = TextDecoration.LineThrough,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .clickable(MutableInteractionSource(), rememberRipple(false, 24.dp, color = Color.White)) {
                                    when {
                                        !checkDescendantOnDone(item.innerList) && item.check -> {
                                            check = !check
                                            item.check = check
                                            save.invoke()
                                            if (item.check) expand = false
                                            trigger.invoke()
                                        }
                                        checkDescendantOnDone(item.innerList) -> {
                                            check = !check
                                            item.check = check
                                            save.invoke()
                                            if (item.check) expand = false
                                            trigger.invoke()
                                        }
                                        else -> showErrorMessage = true
                                    }
                                }
                        )

                    }

/** draggable element */
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .width(300.dp)
                            .offset { IntOffset(dragX.toInt(), 0) }
                            .shadow(8.dp, RoundedCornerShape(12.dp))
                            .background(color = if (item.check) Colors.completedItemBG else bg, shape = RoundedCornerShape(12.dp))
                            .border(width = 1.dp, color = border, shape = RoundedCornerShape(12.dp))
                            .clickable { expand = !expand }
                            .padding(start = 10.dp, 3.dp, 3.dp, 3.dp)
                            .draggable(
                                orientation = Orientation.Horizontal,
                                state = rememberDraggableState { delta -> if (dragX in -10f..75F) dragX += delta },
                                onDragStopped = { dragX = if (dragX > 45) 65F else 0f }
                            )
                    ) {
                        Text(
                            text = if (item.name != "") item.name else "",
                            fontSize = 13.sp,
                            textDecoration = if (check) TextDecoration.LineThrough else TextDecoration.None,
                            color = if (item.check) Colors.completedTextColor else text,
                            modifier = Modifier.weight(1f, false)
                        )


                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            modifier = Modifier.height(IntrinsicSize.Min).padding(horizontal = 5.dp)
                        ) {
                            Text("${getTaskStat(item.innerList).first}", color = Colors.activeTask)
                            Divider(Modifier.fillMaxHeight().width(1.dp), Color.DarkGray, 1.dp)
                            Text("${getTaskStat(item.innerList).second}", color = Colors.completedTask)
                        }
                    }

                }

            }

            AnimatedVisibility(expand) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .background( brush = Brush.horizontalGradient(
                            listOf( Color(255, 255, 255, 75), Color.Transparent ),
                            endX = 200f), RoundedCornerShape(5.dp) )
                        .padding(start = 30.dp, top = 8.dp, bottom = 8.dp)
                ) {

                    spoilerContent.invoke()

                    if (!addTF) {
                        AddBox { addTF = true; editTF = false; tfText = "" }
                    } else {
                        AddEditTF(tfText, {tfText = it}, { addTF = false }) {
                            if (tfText != "") {
                                item.addItem( MainClass(tfText, addDate = TimeEvents().taskAddTime()) )
                                tfText = ""
                                save.invoke()
                                addTF = false
                                trigger.invoke()
                            }
                        }
                    }  /** Box + */

                }
            }

        }

        if (popup) Popup(onDismissRequest = {popup = false}, popupPositionProvider = rememberCursorPositionProvider(), focusable = true) { IconsBox(item, save, trigger) }



    }

}


/** Recursively checking if all hierarchy descendants are done */
fun checkDescendantOnDone(list: List<MainClass>): Boolean {
    return if (list.isNotEmpty()) {
        list.all { it.check } && list.all { checkDescendantOnDone(it.innerList) }
    } else true
}


/** Get number of active and completed tasks */
fun getTaskStat(list: List<MainClass>): Pair<Int, Int> {
    return Pair(list.filter { !it.check }.size, list.filter { it.check }.size)
}
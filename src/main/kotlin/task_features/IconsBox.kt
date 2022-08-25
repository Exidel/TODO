package task_features

import ColorPickerBox
import Colors
import IconPreset
import MainClass
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.rememberDialogState


@Composable
fun IconsBox(
    item: MainClass,
    save: () -> Unit,
    trigger: () -> Unit
) {

    var openDialog by remember { mutableStateOf(false) }

/** Box with icons */
    Box( Modifier.clip(shape = RoundedCornerShape(8.dp)).size(150.dp).background(Color.DarkGray).border(2.dp, Color.LightGray, RoundedCornerShape(8.dp)) ) {

        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                IconPreset(iconPainter = "icons_box/sharp_schedule_black_48dp.png", width = 24, height = 24) {  }
                IconPreset(iconPainter = "icons_box/sharp_timer_black_48dp.png", width = 24, height = 24) {  }
                IconPreset(iconPainter = "icons_box/sharp_alarm_black_48dp.png", width = 24, height = 24) {  }
                IconPreset(iconPainter = "icons_box/statistic_clock_black_48dp.png", width = 24, height = 24) {  }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .size(19.dp, 19.dp)
                        .background( Brush.sweepGradient( listOf(Color.Cyan, Color.Red, Color.Yellow, Color.Green) ) )
                        .clickable { openDialog = true }
                )  /** color picker icon */
            }

        }
    }


/** Color picker dialog */
    if (openDialog) {

        val dialogState = rememberDialogState(size = DpSize(500.dp, 500.dp))

        Dialog(
            onCloseRequest = { openDialog = false },
            state = dialogState,
            title = "Color picker",
            undecorated = true,
            transparent = true,
            resizable = false
        ) {
            Column(
                Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize()
                    .background(Colors.bg)
                    .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
            ) {
                WindowDraggableArea {
                    Box(Modifier.fillMaxWidth().height(40.dp)) {
                        Text("Color picker", Modifier.align(Alignment.Center), Color.White )
                    }
                }
                Divider(Modifier.fillMaxWidth().shadow(8.dp), Color.Gray)
                ColorPickerBox(item, { openDialog = false }, save, trigger)
            }
        }

    }



}
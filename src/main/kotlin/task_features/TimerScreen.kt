package task_features

import IconPreset
import MainClass
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TimeEventsScreen(close: () -> Unit) {

    var ddText by remember { mutableStateOf("Timer") }
    var ddMenu by remember { mutableStateOf(false) }


    Box(Modifier.fillMaxSize().background(Color.DarkGray)) {

/** Back icon */
        Box( Modifier.padding(start = 15.dp, top = 5.dp).align(Alignment.TopStart) ) {
            IconPreset(iconPainter = "round_rollback_black_48dp.png", width = 24, height = 24) { close.invoke() }
        }

/** Dropdown menu */
        Box( Modifier.padding(end = 15.dp, top = 5.dp).align(Alignment.TopEnd).wrapContentSize() ) {

            Box( Modifier.size(100.dp, 20.dp).background(Color.DarkGray).clickable { ddMenu = true } ) { Text(ddText, fontSize = 16.sp, color = Color.White) }

            DropdownMenu(expanded = ddMenu, onDismissRequest = {ddMenu = false}, modifier = Modifier.background(Color.Gray) ) {


                Divider()
                DropdownMenuItem({ ddText = "Timer"; ddMenu = false }) {
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource("icons_box/sharp_timer_black_48dp.png"), null, Modifier.size(24.dp), tint = Color.White)
                        Text("Timer", color = Color.White)
                    }
                }
                Divider()


                DropdownMenuItem({ ddText = "Duration"; ddMenu = false }) {
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource("icons_box/sharp_schedule_black_48dp.png"), null, Modifier.size(24.dp), Color.White)
                        Text("Duration", color = Color.White)
                    }
                }
                Divider()


                DropdownMenuItem({ ddText = "Alarm"; ddMenu = false }) {
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource("icons_box/sharp_alarm_black_48dp.png"), null, Modifier.size(24.dp), tint = Color.White)
                        Text("Alarm", color = Color.White)
                    }
                }
                Divider()


                DropdownMenuItem({ ddText = "Statistic"; ddMenu = false }) {
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource("icons_box/statistic_clock_black_48dp.png"), null, Modifier.size(24.dp), tint = Color.White)
                        Text("Statistic", color = Color.White)
                    }
                }
                Divider()


            }

        }

        Box( Modifier.align(Alignment.Center) ) {
            when (ddText) {
                "Timer" -> TimeEvents().CountDownTimer()
                "Duration" -> TimeEvents().taskDuration(MainClass())
                "Alarm" -> TODO()
                "Statistic" -> TODO()
                else -> Text("Something go wrong!", modifier = Modifier.align(Alignment.Center), color = Color.White)
            }
        }

    }

}
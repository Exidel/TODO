package task_features

import IconPreset
import MainClass
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



@Composable
fun IconsBox(item: MainClass) {

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
                        .clickable {  }
                )  /** color picker */
            }

        }
    }

}
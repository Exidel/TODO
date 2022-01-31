package task_features

import IconPreset
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*


class TimeEvents {


    fun taskAddTime(): String {
        val timeFormat = SimpleDateFormat("dd.MM.yyyy")
        return timeFormat.format(Date())
    }


    fun taskDuration() {}


    @Composable
    fun CountDownTimer(hh: Long = 0, min: Long = 0, sec: Long = 59) {

        var key by remember { mutableStateOf(false) }
        var indicator by remember { mutableStateOf(1f) }
        val timerAnimation = animateFloatAsState(targetValue = indicator, animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec).value

        val totalTimeMillis: Long = timeToMillis(hh, min, sec)
        var currentTime by remember { mutableStateOf(totalTimeMillis) }
        var formattedTime by remember { mutableStateOf(millisToTime(currentTime, 2)) }


        Box(Modifier.fillMaxSize().background(Color.DarkGray)) {

/** Back and front ProgressIndicator */
            CircularProgressIndicator(
                progress = 1f,
                strokeWidth = 10.dp,
                color = Color(255, 255, 255, 50),
                modifier = Modifier.align(Alignment.Center).size(250.dp)
            )

            CircularProgressIndicator(
                progress = timerAnimation,
                strokeWidth = 10.dp,
                color = Color(255, 100, 0, 255),
                modifier = Modifier.align(Alignment.Center).size(250.dp)
            )

/** Timer digits */
            Text(text = formattedTime, color = Color.White, fontSize = 44.sp, modifier = Modifier.align(Alignment.Center))

/** Timer control buttons */
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 30.dp).align(Alignment.BottomCenter)
            ) {
                IconPreset(
                    iconPainter = if (!key) "round_play_arrow_black_48dp.png" else "round_pause_black_48dp.png",
                    width = 40, height = 40, tint = Color(255, 100, 0)
                ) { key = !key }

                IconPreset(
                    iconPainter = "round_stop_black_48dp.png",
                    width = 40, height = 40, tint = Color(255, 100, 0)
                ) { key = false; formattedTime = millisToTime(0, 2); indicator = 0f }

            }

/** Timer calculation function */
            LaunchedEffect(key, currentTime) {
                if (key && indicator > 0.0f) {
                    delay(100)
                    currentTime -= 100
                    formattedTime = millisToTime(currentTime, 2)
                    indicator = (currentTime.toDouble()/totalTimeMillis.toDouble()).toFloat()
                    if (indicator == 0.0f) {key = false}
                }
            }


        }

    }



    /**
     * Converting milliseconds into formatted String (hh:mm:ss.sss)
     * @param duration milliseconds Long
     * @param cutMillisZeroes cuts last 1 or 2 zeroes from milliseconds,
     * value other than 1 or 2 become 0
    */

    private fun millisToTime(duration: Long, cutMillisZeroes: Int = 0, includeDays: Boolean = false): String {
        val millis: Long = duration % 1000
        val seconds: Long = (duration / 1000) % 60
        val minutes: Long = (duration / (1000*60)) % 60
        val hours: Long = (duration / (1000*3600)) % 24
        val days: Long = (duration / (1000*3600)) / 24

        return if (includeDays) {
            String.format("%02d:%02d:%02d.%02d", days, hours, minutes, seconds)
        } else {
            when (cutMillisZeroes) {
                1 -> String.format("%02d:%02d:%02d.%02d", hours, minutes, seconds, millis/10)
                2 -> String.format("%02d:%02d:%02d.%d", hours, minutes, seconds, millis/100)
                else -> String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis)
            }
        }
    }

    /**
     * Converting parameters value into milliseconds
     * @param hh range 0..23
     * @param min range 0..59
     * @param sec range 0..59
    */

    private fun timeToMillis(hh: Long = 0, min: Long = 0, sec: Long = 0): Long {
        val seconds: Long = if (sec in 0..59) sec else 59
        val minutes: Long = if (min in 0..59) min else 59
        val hours: Long = if (hh in 0..23) hh else 23

        return (((hours * 3600) + (minutes * 60) + seconds) * 1000)
    }


}
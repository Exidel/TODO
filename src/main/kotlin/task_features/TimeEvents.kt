package task_features

import java.text.SimpleDateFormat
import java.util.*


interface TimeEvents {

    fun taskAddTime(): String {
        val timeFormat = SimpleDateFormat("dd.MM.yyyy")
        return timeFormat.format(Date())
    }

    fun timer(hh: Int, min: Int, sec: Int) {}

    fun taskDuration() {}

}
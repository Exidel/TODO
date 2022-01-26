package task_features

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState


@Composable
fun TimerScreen() {

    val dialog = rememberDialogState(position = WindowPosition(Alignment.Center))
    var showDialog by remember { mutableStateOf(true) }



    if (showDialog) {

        Dialog(state = dialog, onCloseRequest = {showDialog = false}) {
            TimeEvents().CountDownTimer()
        }

    }


}
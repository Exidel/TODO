import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition


class Settings(
    var size: DpSize = DpSize(400.dp, 500.dp),
    var position: WindowPosition = WindowPosition(Alignment.Center)
)
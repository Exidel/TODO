import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddIcon(add: () -> Unit) {

    TooltipArea(
        tooltip = {
            Text(
                text = "Add task",
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(4.dp)
            ) },
        delayMillis = 100,
        tooltipPlacement = TooltipPlacement.CursorPoint(DpOffset(0.dp, (-10).dp), Alignment.TopCenter),
    ) {
        IconButton( onClick = {
            add.invoke()
        },
            modifier = Modifier.size(48.dp, 48.dp)
        ) { Icon(imageVector = Icons.Rounded.Send, contentDescription = null, tint = Color.White) }
    }

}
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TooltipPreset(text: String, content: @Composable () -> Unit) {

    TooltipArea(
        tooltip = {
            Text(
                text = text,
                modifier = Modifier
                    .background( Color.White, RoundedCornerShape(12.dp) )
                    .padding(4.dp)
            )
          },
        delayMillis = 100,
        tooltipPlacement = TooltipPlacement.CursorPoint(offset = DpOffset(0.dp, (-10).dp), alignment = Alignment.TopCenter)
    ) {
        content.invoke()
    }

}
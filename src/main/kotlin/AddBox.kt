import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddBox(addState: () -> Unit) {

    Box {
        Text(
            text = "+",
            color = Color.White,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(300.dp)
                .background(color = Color.Transparent, shape = RoundedCornerShape(12.dp))
                .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
                .clip(shape = RoundedCornerShape(12.dp))
                .clickable { addState.invoke() }
                .padding(bottom =  3.dp, top =  3.dp)
        )
    }

}
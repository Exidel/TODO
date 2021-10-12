import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskListItems(text: String) {

    Box(Modifier.padding(2.dp).fillMaxWidth()) {

        Text(
            text = text,
            modifier = Modifier
                .padding(end = 22.dp)
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .background(color = Color.LightGray, shape = RoundedCornerShape(6.dp))
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(6.dp))
                .padding(5.dp)
        )

        IconButton( onClick = {
            //TODO play recorded file
        },
            modifier = Modifier
                .size(20.dp, 20.dp)
                .align(Alignment.CenterEnd)
        ) { Icon(Icons.Rounded.PlayArrow, null) }

    }

}
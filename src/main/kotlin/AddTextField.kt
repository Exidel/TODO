import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddEditTF(
    name: String,
    nameChange: (String) -> Unit,
    close: () -> Unit,
    add: () -> Unit
) {

    var tfText by remember { mutableStateOf(name) }

    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

        BasicTextField(
            value = tfText,
            onValueChange = {tfText = it},
            singleLine = true,
            modifier = Modifier
                .width(300.dp)
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp))
                .clip(shape = RoundedCornerShape(12.dp))
                .onKeyEvent {
                    if ((it.key == Key.Enter) && (it.type == KeyEventType.KeyUp)) {
                        if (tfText != "") {
                            nameChange(tfText)
                            tfText = ""
                            add.invoke()
                        }
                        true
                    } else {false}
                }
                .padding(start = 10.dp, 3.dp, 3.dp, 3.dp)
        )

        IconPreset(Icons.Rounded.Close) { tfText = ""; close.invoke() }
        IconPreset(Icons.Rounded.Check) { nameChange(tfText); tfText = ""; add.invoke() }

    }

}
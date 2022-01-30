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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddEditTF(
    name: String,
    nameChange: (String) -> Unit,
    close: () -> Unit,
    add: () -> Unit
) {

    var tfText by remember { mutableStateOf(TextFieldValue( name, TextRange(name.length) ) ) }
    val focusRequest = remember { FocusRequester() }

    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

        BasicTextField(
            value = tfText,
            onValueChange = {tfText = it},
            singleLine = true,
            modifier = Modifier
                .width(300.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .border(width = 3.dp, color = Color(0, 100, 255, 255), shape = RoundedCornerShape(12.dp))
                .clip(shape = RoundedCornerShape(12.dp))
                .focusRequester(focusRequest)
                .onKeyEvent {

                    if ((it.key == Key.Enter) && (it.type == KeyEventType.KeyUp)) {
                        if (tfText.text != "") {
                            nameChange(tfText.text)
                            add.invoke()
                        }
                        true
                    } else if ((it.key == Key.Escape) && (it.type == KeyEventType.KeyUp)) {
                        close.invoke()
                        true
                    } else {false}

                }
                .padding(start = 10.dp, 3.dp, 3.dp, 3.dp)
        )

        IconPreset(Icons.Rounded.Close) { close.invoke() }
        IconPreset(Icons.Rounded.Check) { nameChange(tfText.text);  add.invoke() }

        LaunchedEffect(Unit){ focusRequest.requestFocus() }

    }

}
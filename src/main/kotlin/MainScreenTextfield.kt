import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreenTF(text: String, textChange: (String) -> Unit, add: () -> Unit) {

    var tfState by remember { mutableStateOf(text) }

    OutlinedTextField(
        value = tfState,
        onValueChange = { tfState = it; textChange(tfState) },
        singleLine = true,
        label = { Text("Enter new task") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            disabledTextColor = Color.Gray,
            cursorColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Gray
        ),
        modifier = Modifier
            .width(280.dp)
            .onKeyEvent {
            if ((it.key == Key.Enter) && (it.type == KeyEventType.KeyUp)) {
                add.invoke()
                tfState = ""
                true
            } else {false}
        }
    )

}
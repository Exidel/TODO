import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconPreset(icon: ImageVector, onClick: () -> Unit) {

    IconButton(onClick = { onClick.invoke() }, modifier = Modifier.size(19.dp, 19.dp)) {
        Icon(imageVector = icon, null, tint = Color.White)
    }

}
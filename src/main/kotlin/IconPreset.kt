import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconPreset(
    iconImageVector: ImageVector? = null,
    iconBitmap: ImageBitmap? = null,
    iconPainter: String? = null,
    width: Int = 19,
    height: Int = 19,
    tint: Color = Color.White,
    onClick: () -> Unit
) {

    if ((iconImageVector != null) && iconBitmap == null && iconPainter == null) {

        IconButton(onClick = { onClick.invoke() }, modifier = Modifier.size(width.dp, height.dp)) {
            Icon( imageVector = iconImageVector, null, tint = tint )
        }

    } else if ((iconImageVector == null) && iconBitmap != null && iconPainter == null) {

        IconButton(onClick = { onClick.invoke() }, modifier = Modifier.size(width.dp, height.dp)) {
            Icon( bitmap = iconBitmap, null, tint = tint )
        }

    } else if ((iconImageVector == null) && iconBitmap == null && iconPainter != null) {

        IconButton(onClick = { onClick.invoke() }, modifier = Modifier.size(width.dp, height.dp)) {
            Icon( painter = painterResource(iconPainter), null, tint = tint )
        }

    } else { println("Don't be dumb, delete 1 icon from parameters!") }

}
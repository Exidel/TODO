import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun IconPreset(
    iconImageVector: ImageVector? = null,
    iconBitmap: ImageBitmap? = null,
    iconPainter: String? = null,
    width: Int = 19,
    height: Int = 19,
    tint: Color = Color.White,
    mod: Modifier = Modifier.size(width = width.dp, height = height.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication = rememberRipple(false, 24.dp, Color.White),
    onClick: () -> Unit
) {

    if ( (iconImageVector != null) && (iconBitmap == null) && (iconPainter == null) ) {

        CustomIconButton( onClick = { onClick.invoke() }, modifier = mod, interactionSource = interactionSource, indication = indication ) {
            Icon( imageVector = iconImageVector, null, tint = tint )
        }

    } else if ( (iconImageVector == null) && (iconBitmap != null) && (iconPainter == null) ) {

        CustomIconButton( onClick = { onClick.invoke() }, modifier = mod, interactionSource = interactionSource, indication = indication ) {
            Icon( bitmap = iconBitmap, null, tint = tint )
        }

    } else if ( (iconImageVector == null) && (iconBitmap == null) && (iconPainter != null) ) {

        CustomIconButton( onClick = { onClick.invoke() }, modifier = mod, interactionSource = interactionSource, indication = indication ) {
            Icon( painter = painterResource(iconPainter), null, tint = tint )
        }

    } else { println("Don't be dumb, delete 1 icon from parameters!") }

}



@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication = rememberRipple(false, 24.dp, Color.White),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = indication
            ),
        contentAlignment = Alignment.Center
    ) {
        val contentAlpha = if (enabled) LocalContentAlpha.current else ContentAlpha.disabled
        CompositionLocalProvider(LocalContentAlpha provides contentAlpha, content = content)
    }
}
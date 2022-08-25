package task_features.color_picker

import TooltipPreset
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun UserColorPalette(
    preset: ColorPalette,
    bg: Color,
    border: Color,
    text: Color,
    bgLamb: (Color) -> Unit,
    borderLamb: (Color) -> Unit,
    textLamb: (Color) -> Unit,
    delete: () -> Unit,
    applyColors: (Color, Color, Color) -> Unit,
    saveChanges: (Color, Color, Color) -> Unit
) {

    val colors = listOf(
        Color(preset.color1[0], preset.color1[1], preset.color1[2], preset.color1[3]),
        Color(preset.color2[0], preset.color2[1], preset.color2[2], preset.color2[3]),
        Color(preset.color3[0], preset.color3[1], preset.color3[2], preset.color3[3])
    )
    var edit by remember { mutableStateOf(false) }
    var color1 by remember(preset) { mutableStateOf(colors[0]) }
    var color2 by remember(preset) { mutableStateOf(colors[1]) }
    var color3 by remember(preset) { mutableStateOf(colors[2]) }


    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 5.dp)
    ) {

        TooltipPreset("apply colors to item") {
            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp).clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = false, color = Color.White)
                ) { applyColors(color1, color2, color3) }
            )
        }

        TooltipPreset(if (edit) "save changes" else "edit template") {
            Icon(
                imageVector = if (edit) Icons.Rounded.Done else Icons.Rounded.Edit,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp).clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = false, color = Color.White)
                ) {
                    if (edit && ((color1 == bg) || (color2 == border) || (color3 == text)) ) saveChanges(color1, color2, color3)
                    edit = !edit
                }
            )
        }

        TooltipPreset("delete template") {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp).clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = false, color = Color.White)
                ) { delete.invoke() }
            )
        }

        TooltipPreset(if (edit) "set background color" else "apply background color") {
            Box(
                Modifier
                    .size(18.dp)
                    .background(color1)
                    .border(1.dp, Color.White)
                    .clickable { if (edit) color1 = bg else bgLamb(color1) }
            )
        }

        TooltipPreset(if (edit) "set border color" else "apply border color") {
            Box(
                Modifier
                    .size(18.dp)
                    .background(color2)
                    .border(1.dp, Color.White)
                    .clickable { if (edit) color2 = border else borderLamb(color2) }
            )
        }

        TooltipPreset(if (edit) "set text color" else "apply text color") {
            Box(
                Modifier
                    .size(18.dp)
                    .background(color3)
                    .border(1.dp, Color.White)
                    .clickable { if (edit) color3 = text else textLamb(color3) }
            )
        }

    }

}
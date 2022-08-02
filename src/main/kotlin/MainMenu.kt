import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BoxScope.MainMenu(
    resetWindow: () -> Unit,
    exit: () -> Unit
) {

    var expand by remember { mutableStateOf(false) }

    Box(
        Modifier
            .wrapContentSize()
            .size(40.dp)
            .align(Alignment.CenterStart)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple( color = Color(250,250,250) )
            ) { expand = !expand }) {

        Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )


        DropdownMenu(
            expanded = expand,
            onDismissRequest = {expand = false},
            modifier = Modifier
                .background(Color.DarkGray, RoundedCornerShape(4.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
        ) {

            Text(text = Labels.resetWindow, maxLines = 1, color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple( color = Color(250,250,250) )
                ) { resetWindow.invoke() }
                .padding(10.dp, 4.dp)
            )

            Divider(color = Color.LightGray)


            Text(text = Labels.exit, maxLines = 1, color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple( color = Color(250,250,250) )
                ) { exit.invoke() }
                .padding(10.dp, 4.dp)
            )

        }
    }

}
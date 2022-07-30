import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ApplicationTopBar(state: WindowState, exitApp: () -> Unit) {

    var hover by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background( Color(60, 60, 60) )
    ) {

// LOGO
        Image(painter = painterResource("logo.png"), contentDescription = null, modifier = Modifier.align(Alignment.Center))


// Menu
        Box(
            Modifier
                .size(40.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple( color = Color(250,250,250) )
                ) {  }
        ) {
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }


// Window icons
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {

            // Minimize
            Box(
                Modifier
                    .size(40.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple( color = Color(250,250,250) )
                    ) { state.isMinimized = !state.isMinimized }
            ) {
                Icon(
                    painter = painterResource("round_minimize_black_24dp.png"),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp).align(Alignment.Center)
                )
            }


            // Full Screen
            Box(
                Modifier
                    .size(40.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple( color = Color(250,250,250) )
                    ) {
                        state.placement =
                            if (state.placement == WindowPlacement.Maximized) WindowPlacement.Floating else WindowPlacement.Maximized
                    }
            ) {
                Icon(
                    painter = painterResource(
                        if (state.placement == WindowPlacement.Floating) "round_maximize_black_48dp.png" else "round_maximized_black_48dp.png"
                    ),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(12.dp).align(Alignment.Center)
                )
            }


            // Close
            Box(
                Modifier
                    .size(40.dp)
                    .background(if (hover) Color(0.8f,0.2f,0.2f) else Color.Transparent)
                    .onPointerEvent(PointerEventType.Enter) { hover = true }
                    .onPointerEvent(PointerEventType.Exit) { hover = false }
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple( color = Color(0f,0f,0f) )
                    ) { exitApp.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp).align(Alignment.Center)
                )
            }


        }
    }

}
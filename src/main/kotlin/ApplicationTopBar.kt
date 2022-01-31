import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState


@Composable
fun ApplicationTopBar(state: WindowState, exitApp: () -> Unit) {

    Box(
        Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background( Color(60, 60, 60) )
    ) {

        Image(painter = painterResource("logo.png"), contentDescription = null, modifier = Modifier.align(Alignment.Center))

        IconButton(onClick = {}) { Icon( Icons.Rounded.Menu, null, Modifier.size(48.dp), Color.White ) }

        Row(
            Modifier
                .padding(end = 5.dp)
                .align(Alignment.CenterEnd),
            Arrangement.spacedBy(15.dp),
            Alignment.CenterVertically
        ) {

            IconPreset(iconPainter = "round_minimize_black_24dp.png", width = 20, height = 20) { state.isMinimized = !state.isMinimized }


            IconPreset(
                iconPainter = if (state.placement == WindowPlacement.Floating) "round_maximize_black_48dp.png" else "round_maximized_black_48dp.png",
                width = 12, height = 12
            ) {
                state.placement =
                    if (state.placement == WindowPlacement.Maximized) WindowPlacement.Floating else WindowPlacement.Maximized
            }


            IconPreset(Icons.Rounded.Close, width = 20, height = 20) { exitApp.invoke() }


        }
    }

}
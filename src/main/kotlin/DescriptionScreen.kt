import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionScreen(mainList: SnapshotStateList<MainClass>, closeDescription: (Boolean) -> Unit, index: Int) {

    Box(Modifier.fillMaxSize()) {

        Box(Modifier.size(50.dp, 50.dp).align(Alignment.Center).background(Color.Red).clickable { closeDescription(false) }) {
            Text("$index")
        }

    }

}
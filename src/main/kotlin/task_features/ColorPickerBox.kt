import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ColorPickerBox(
    colors: List<Color>,
    cancel: () -> Unit,
    newColors: (List<Color>) -> Unit
) {

    var bgColor by remember { mutableStateOf(colors.first()) }
    var borderColor by remember { mutableStateOf(colors[1]) }
    var textColor by remember { mutableStateOf(colors.last()) }

    var bg by remember { mutableStateOf(true) }
    var border by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(false) }

    var bgColorUpdated by remember { mutableStateOf(bgColor) }
    var borderColorUpdated by remember { mutableStateOf(borderColor) }
    var textColorUpdated by remember { mutableStateOf(textColor) }

    Box(Modifier.fillMaxSize().padding(15.dp, 15.dp)) {

        Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxSize()) {

            when {
                bg && !border && !text -> ColorPicker(bgColor, Color.White) { bgColorUpdated = it }
                !bg && border && !text -> ColorPicker(borderColor, Color.White) { borderColorUpdated = it }
                !bg && !border && text -> ColorPicker(textColor, Color.White) { textColorUpdated = it }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {


                Box(
                    Modifier
                        .width(250.dp)
                        .background(bgColorUpdated, RoundedCornerShape(12.dp))
                        .border(1.dp, borderColorUpdated, RoundedCornerShape(12.dp))
                        .padding(10.dp, 4.dp)
                ) { Text("Sample Text", color = textColorUpdated) }


                Box(
                    Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .clickable {
                            bg = true; border = false; text = false
                            borderColor = borderColorUpdated; textColor = textColorUpdated
                        }
                        .padding(4.dp)
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Red, RoundedCornerShape(4.dp))
                    )
                }


                Box(
                    Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
                            bg = false; border = true; text = false
                            bgColor = bgColorUpdated; textColor = textColorUpdated
                        }
                        .padding(4.dp)
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .border(2.dp, Color.Red, RoundedCornerShape(4.dp))

                    )
                }


                Text(
                    text = "Aa",
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
                            bg = false; border = false; text = true
                            bgColor = bgColorUpdated; borderColor = borderColorUpdated
                        }
                        .padding(2.dp)
                )


            }


            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.End).padding(top = 20.dp, end = 20.dp)
            ) {


                Box(
                    Modifier
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            newColors( listOf(bgColorUpdated, borderColorUpdated, textColorUpdated) )
                            cancel.invoke()
                        }
                        .padding(10.dp, 4.dp)
                ) {
                    Text("Save", color = Color.LightGray, modifier = Modifier.align(Alignment.Center))
                }


                Box(
                    Modifier
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { cancel.invoke() }
                        .padding(10.dp, 4.dp)
                ) {
                    Text("Cancel", color = Color.LightGray, modifier = Modifier.align(Alignment.Center))
                }


            }


        }

    }

}
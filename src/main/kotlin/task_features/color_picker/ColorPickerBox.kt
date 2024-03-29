import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import task_features.color_picker.ColorPalette
import task_features.color_picker.ColorPicker
import task_features.color_picker.UserColorPalette


@Composable
fun ColorPickerBox(
    item: MainClass,
    cancel: () -> Unit,
    save: () -> Unit,
    trigger: () -> Unit
) {

/** Color manipulation variables */
    var bgColor by remember { mutableStateOf(Color(item.bg[0], item.bg[1], item.bg[2], item.bg[3])) }
    var borderColor by remember { mutableStateOf(Color(item.border[0], item.border[1], item.border[2], item.border[3])) }
    var textColor by remember { mutableStateOf(Color(item.text[0], item.text[1], item.text[2], item.text[3])) }

    var bg by remember { mutableStateOf(true) }
    var border by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(false) }

    var bgColorUpdated by remember { mutableStateOf(bgColor) }
    var borderColorUpdated by remember { mutableStateOf(borderColor) }
    var textColorUpdated by remember { mutableStateOf(textColor) }

/** User palette variables */
    val paletteList = remember { JsonFileOperations.loadColorPalette() }
    val scroll = rememberScrollState()


    Box(Modifier.fillMaxSize().padding(15.dp, 15.dp)) {

        Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxSize()) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                when {
                    bg && !border && !text -> ColorPicker(bgColor, Color.White) { bgColorUpdated = it }
                    !bg && border && !text -> ColorPicker(borderColor, Color.White) { borderColorUpdated = it }
                    !bg && !border && text -> ColorPicker(textColor, Color.White) { textColorUpdated = it }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.height(255.dp).verticalScroll(scroll)
                ) {
                    paletteList.forEachIndexed { _index, _item ->

                        UserColorPalette(
                            preset = _item,
                            bg = bgColorUpdated,
                            border = borderColorUpdated,
                            text = textColorUpdated,
                            bgLamb = { bgColor = it; bgColorUpdated = it },
                            borderLamb = { borderColor = it; borderColorUpdated = it },
                            textLamb = { textColor = it; textColorUpdated = it },
                            delete = { paletteList.removeAt(_index) },
                            applyColors = { _bg, _border, _text ->
                                bgColor = _bg
                                borderColor = _border
                                textColor = _text
                                bgColorUpdated = _bg
                                borderColorUpdated = _border
                                textColorUpdated = _text
                            },
                            saveChanges = { _bg, _border, _text ->
                                paletteList.set(
                                    index = _index,
                                    element = ColorPalette(
                                        color1 = listOf(_bg.red, _bg.green, _bg.blue, _bg.alpha),
                                        color2 = listOf(_border.red, _border.green, _border.blue, _border.alpha),
                                        color3 = listOf(_text.red, _text.green, _text.blue, _text.alpha)
                                    )
                                )
                            }
                        )
                    }

                    Icon(
                        imageVector = Icons.Rounded.AddCircle,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp).clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = rememberRipple(bounded = false, color = Color.White)
                        ) { paletteList.add(ColorPalette()) }
                    )

                }

            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {

/** Item representation */
                Box(
                    Modifier
                        .width(250.dp)
                        .background(bgColorUpdated, RoundedCornerShape(12.dp))
                        .border(1.dp, borderColorUpdated, RoundedCornerShape(12.dp))
                        .padding(10.dp, 4.dp)
                ) { Text("Sample Text", color = textColorUpdated) }

/** Icon background */
                Box(
                    Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, if (bg) Color.LightGray else Color.Transparent, RoundedCornerShape(4.dp))
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

/** Icon border */
                Box(
                    Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, if (border) Color.LightGray else Color.Transparent, RoundedCornerShape(4.dp))
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

/** Icon text */
                Text(
                    text = "Aa",
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, if (text) Color.LightGray else Color.Transparent, RoundedCornerShape(4.dp))
                        .clickable {
                            bg = false; border = false; text = true
                            bgColor = bgColorUpdated; borderColor = borderColorUpdated
                        }
                        .padding(2.dp)
                )


            }

/** Row with buttons */
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
                            bgColor = Colors.itemBG
                            borderColor = Colors.itemBorder
                            textColor = Colors.textColor
                            bgColorUpdated = Colors.itemBG
                            borderColorUpdated = Colors.itemBorder
                            textColorUpdated = Colors.textColor
                        }
                        .padding(10.dp, 4.dp)
                ) {
                    Text("Reset", color = Color.LightGray, modifier = Modifier.align(Alignment.Center))
                }


                Box(
                    Modifier
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            item.bg = listOf(bgColorUpdated.red, bgColorUpdated.green, bgColorUpdated.blue, bgColorUpdated.alpha)
                            item.border = listOf(borderColorUpdated.red, borderColorUpdated.green, borderColorUpdated.blue, borderColorUpdated.alpha)
                            item.text = listOf(textColorUpdated.red, textColorUpdated.green, textColorUpdated.blue, textColorUpdated.alpha)
                            save.invoke()
                            trigger.invoke()
                            JsonFileOperations.createColorPalette(paletteList.toList())
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
                        .clickable {
                            JsonFileOperations.createColorPalette(paletteList.toList())
                            cancel.invoke()
                        }
                        .padding(10.dp, 4.dp)
                ) {
                    Text("Cancel", color = Color.LightGray, modifier = Modifier.align(Alignment.Center))
                }


            }


        }

    }

}
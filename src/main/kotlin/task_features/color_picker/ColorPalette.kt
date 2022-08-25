package task_features.color_picker

import kotlinx.serialization.Serializable

@Serializable
class ColorPalette(
    var color1: List<Float> = listOf(0.8f, 0.8f, 0.8f, 1f),
    var color2: List<Float> = listOf(0.8f, 0.8f, 0.8f, 1f),
    var color3: List<Float> = listOf(0.8f, 0.8f, 0.8f, 1f)
)
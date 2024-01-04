package core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ui.model.SelectableColor
import kotlin.math.absoluteValue

fun String.toHslColor(saturation: Float = 0.5f, lightness: Float = 0.4f): Color {
    val hue = fold(0) { acc, char -> char.code + acc } / (length * 1000)
    return Color(hue.absoluteValue.toFloat(), saturation, lightness)
}
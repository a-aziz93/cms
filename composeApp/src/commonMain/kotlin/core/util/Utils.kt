package core.util

import androidx.compose.ui.graphics.Color
import kotlin.math.absoluteValue

fun String.toHslColor(saturation: Float = 0.5f, lightness: Float = 0.4f): Color {
    val hue = fold(0) { acc, char -> char.code+ acc }/(length*1000)
    return Color(hue.absoluteValue.toFloat(), saturation, lightness)
}
package ui.component.avatar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import core.util.toHslColor

@Composable
fun InitialsAvatar(
    firstName: String,
    lastName: String,
    size: Dp = 40.dp,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    onClick: (() -> Unit)? = null,
) {
    var modifier = Modifier
        .clip(shape = CircleShape)
        .size(size)
    if (onClick != null) {
        modifier = modifier.clickable {
            onClick()
        }
    }
    Box(modifier, contentAlignment = Alignment.Center) {
        val color = remember(firstName, lastName) {
            val name = listOf(firstName, lastName)
                .joinToString(separator = "")
                .uppercase()
            name.toHslColor()
        }
        val initials = (firstName.take(1) + lastName.take(1)).uppercase()
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(SolidColor(color))
        }
        Text(text = initials, style = textStyle, color = Color.White)
    }
}
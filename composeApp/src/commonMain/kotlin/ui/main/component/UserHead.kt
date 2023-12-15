package ui.main.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi=
import core.util.toHslColor
=
@Composable
fun UserHead(
    avatar:@Composable ()->Unit={},
    avatarProvided:Boolean=false,
    id: String,
    firstName: String,
    lastName: String,
    role:String,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    onClick:()->Unit={},
    ) {
        Column (Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier.size(size).clickable { onClick() }, contentAlignment = Alignment.Center) {
                if(avatarProvided){
                    avatar()
                }else{
                    val color = remember(id, firstName, lastName) {
                        val name = listOf(firstName, lastName)
                            .joinToString(separator = "")
                            .uppercase()
                        "$id / $name".toHslColor()
                    }
                    val initials = (firstName.take(1) + lastName.take(1)).uppercase()
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(SolidColor(color))
                    }
                    Text(text = initials, style = textStyle, color = Color.White)
                }
            }
            Text(text=role,style=MaterialTheme.typography.labelMedium)
        }
}
package ui.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun LandingUi(component: LandingComponent) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Welcome!",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Sign in or create a new account",
                    fontSize = 20.sp,
                    modifier = Modifier.alpha(.3f)
                )
            }


            Column(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                AsyncImage(
//                    model = "https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/76522/hot-air-balloon-clipart-xl.png",
//                    contentDescription = stringResource(id = R.string.welcome_screen_image)
//                )
            }
            Box(
                modifier = Modifier
                    .width(400.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {
                            component.onOutput(LandingComponent.Output.NavigateToMain)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .absolutePadding(bottom = 5.dp),
                    ) {
                        Text(text = "Sign In")
                    }

                    OutlinedButton(
                        onClick = { },
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .absolutePadding(top = 5.dp)
                    ) {
                        Text(
                            text = "Sign Up",
                            color = Color(red = 255, green = 125, blue = 0)
                        )

                    }
                }
            }
        }
    }
}
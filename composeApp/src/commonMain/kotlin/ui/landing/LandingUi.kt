package ui.landing

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.kamel.image.asyncPainterResource
import ui.common.component.carousel.Carousel
import ui.common.component.banner.Banner
import ui.root.RootComponent
import java.io.File

@Composable
internal fun LandingUi(component: LandingComponent) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Carousel(
            items = listOf(
                {
                    Banner(
                        resource = asyncPainterResource(data = "https://t4.ftcdn.net/jpg/02/18/18/55/360_F_218185587_P4zituDtWJOfClUKL6merI0BgLMIxoeC.jpg"),
                    )
                },
                {
                    Banner(
                        resource = asyncPainterResource(data = "/drawable/banner/hand.jpg"),
                    )
                },
                {
                    Banner(
                        resource = asyncPainterResource(data = File("/drawable/banner/watch.jpg")),
                    )
                },
            ),
            onItemClicked = {

            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                modifier = Modifier
                    .width(600.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {
                            component.onNavigate(RootComponent.Config.Main)
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
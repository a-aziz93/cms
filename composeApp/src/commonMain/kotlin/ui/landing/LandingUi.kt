package ui.landing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.kamel.image.asyncPainterResource
import ui.common.component.pager.carousel.Carousel
import ui.common.component.banner.Banner
import ui.root.RootComponent
import java.io.File
import cafe.adriel.lyricist.strings

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun LandingUi(component: LandingComponent) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Carousel(
            modifier = Modifier
                .height(400.dp),
            horizontal = false,
            items = listOf(
                "/drawable/banner/customs.jpg",
                "/drawable/banner/hand.jpg",
                "/drawable/banner/watch.jpg"
            ).map {
                {
                    Banner(
                        resource = asyncPainterResource(
                            data = File(
                                this::class.java.getResource(it).getPath()
                            )
                        ),
                    )
                }
            },
        )
        Text(
            text = "Welcome to the Electronic Border App",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Column(
            modifier = Modifier.width(200.dp)
        ) {
            Button(
                onClick = {
                    component.onNavigate(RootComponent.Config.Main)
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(strings.home)
            }
            Button(
                onClick = {
                    component.onNavigate(RootComponent.Config.Main)
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(strings.signIn)
            }
            Button(
                onClick = { },
                Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    strings.signUp,
                )
            }
        }
    }
}
package ui.selfsignup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Eye
import compose.icons.evaicons.outline.EyeOff2
import coroutineDispatchers
import org.jetbrains.compose.resources.painterResource


import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignUpUi(component: SelfSignUpComponent) {

    val login =component.login.collectAsState(coroutineDispatchers.main)
    val password = component.password.collectAsState(coroutineDispatchers.main)
    val inProgress = component.inProgress.collectAsState()

    val image = painterResource("compose-multiplatform.xml")

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(600.dp)
                .height(600.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(image, contentDescription = null)
                }
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = emailValue.value,
                        onValueChange = {
emailValue.value=it
                        },
                        label = { Text("Name") },
                        placeholder = { Text("Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )
                    OutlinedTextField(
                        value = emailValue.value,
                        onValueChange = {

                        },
                        label = { Text("Email Address") },
                        placeholder = { Text("Email Address") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )

                     OutlinedTextField(
                        value = emailValue.value,
                        onValueChange = {

                        },
                        label = { Text("Phone Number") },
                        placeholder = { Text("Phone Number") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )

                    OutlinedTextField(
                        value = passwordValue.value,
                        onValueChange = {

                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                                Icon(
                                    imageVector = if (passwordVisibility.value) EvaIcons.Outline.EyeOff2 else EvaIcons.Outline.Eye,
                                    contentDescription = null
                                )
                            }
                        },
                        label = { Text("Password") },
                        placeholder = { Text("Password") },
                        singleLine = true,
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .focusRequester(focusRequester = focusRequester),

                        )
                    OutlinedTextField(
                        value = passwordValue.value,
                        onValueChange = {

                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                                Icon(
                                    imageVector = if (passwordVisibility.value) EvaIcons.Outline.EyeOff2 else EvaIcons.Outline.Eye,
                                    contentDescription = null
                                )
                            }
                        },
                        label = { Text("Confirm Password") },
                        placeholder = { Text("Confirm Password") },
                        singleLine = true,
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .focusRequester(focusRequester = focusRequester),

                        )

                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text(text = "Sign Up", fontSize = 20.sp)
                    }
                }

            }
        }
    }
}


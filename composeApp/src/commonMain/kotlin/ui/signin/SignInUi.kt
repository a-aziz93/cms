package ui.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import coroutineDispatchers

@Composable
fun SignInUi(component: SignInComponent) {

    val login = component.login.collectAsState(coroutineDispatchers.main)
    val password = component.password.collectAsState(coroutineDispatchers.main)
    val inProgress = component.inProgress.collectAsState()

    Column {
        TextField(
            value = login.value, 
            onValueChange = component::onLoginChanged
        )

        TextField(
            value = password.value,
            onValueChange = component::onPasswordChanged,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (inProgress.value) {
            CircularProgressIndicator()
        } else {
            Button(onClick = component::onSignInClick){}
        }
    }
}


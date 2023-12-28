package ui.reset

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import core.coroutineDispatchers

@Composable
fun ResetUi(component: ResetComponent) {

    val login =component.login.collectAsState(coroutineDispatchers.main)
    val inProgress = component.inProgress.collectAsState()

    Column {
        TextField(
            value = login.value, 
            onValueChange = component::onLoginChanged
        )

        if (inProgress.value) {
            CircularProgressIndicator()
        } else {
            Button(onClick = component::onResetClick){}
        }
    }
}


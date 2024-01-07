package ui.common.component.globe

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import earth.worldwind.WorldWindow

@Composable
actual fun WorldWindGlobe() {
    // Use AndroidView to host the WorldWindow
    AndroidView(factory = { context ->
        WorldWindow(context).apply {
            // Configure your WorldWindow here
            // For example, set layers, handle touch events, etc.
        }
    })
}

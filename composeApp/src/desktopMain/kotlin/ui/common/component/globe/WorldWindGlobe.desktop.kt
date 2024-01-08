package ui.common.component.globe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import gov.nasa.worldwind.BasicModel
import gov.nasa.worldwind.awt.WorldWindowGLCanvas
import java.awt.BorderLayout
import javax.swing.JPanel

class WorldWindPanel : JPanel() {
    init {
        layout = BorderLayout()
        add(WorldWindowGLCanvas().apply {
            model = BasicModel()
        }, BorderLayout.CENTER)
    }
}

@Composable
actual fun WorldWindGlobe() {
    SwingPanel(
        factory = { WorldWindPanel() },
        modifier = Modifier.fillMaxSize()
    )
}
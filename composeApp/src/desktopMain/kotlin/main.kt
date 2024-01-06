import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import core.di.initKoin
import org.koin.core.context.stopKoin
import ui.root.DefaultRootComponent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import core.util.runOnUiThread
import core.util.saveStateToFile
import core.util.tryRestoreStateFromFile
import ui.adminsignup.AdminSignUpUi
import ui.adminsignup.PreviewAdminSignUpComponent
import ui.cdex.CDexUi
import ui.cdex.PreviewCDexComponent
import ui.cdox.CDoxUi
import ui.cdox.PreviewCDoxComponent
import ui.cms.CMSUi
import ui.cms.PreviewCMSComponent
import ui.component.SaveStateDialog
import ui.dashboard.DashboardUi
import ui.dashboard.PreviewDashboardComponent
import ui.dynamicfeatures.dynamicfeature.DefaultFeatureInstaller
import ui.home.HomeUi
import ui.home.PreviewHomeComponent
import ui.landing.LandingUi
import ui.landing.PreviewLandingComponent
import ui.main.MainUi
import ui.main.PreviewMainComponent
import ui.map.MapUi
import ui.map.PreviewMapComponent
import ui.profile.PreviewProfileComponent
import ui.profile.ProfileUi
import ui.queue.PreviewQueueComponent
import ui.queue.QueueUi
import ui.reset.PreviewResetComponent
import ui.reset.ResetUi
import ui.root.PreviewRootComponent
import ui.settings.PreviewSettingsComponent
import ui.settings.SettingsUi
import ui.signin.PreviewSignInComponent
import ui.signin.SignInUi
import ui.selfsignup.PreviewSelfSignUpComponent
import ui.selfsignup.SelfSignUpUi
import ui.theme.AppTheme

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    initKoin()
  
    val lifecycle = LifecycleRegistry()
    val stateKeeper = StateKeeperDispatcher(tryRestoreStateFromFile())

    val rootComponent =
        runOnUiThread {
            val rootComponent=  DefaultRootComponent(
                componentContext = DefaultComponentContext(
                    lifecycle = lifecycle,
                    stateKeeper = stateKeeper,
                    ),
                storeFactory = DefaultStoreFactory(),
                featureInstaller = DefaultFeatureInstaller,
                )
            
            lifecycle.resume()
            
            rootComponent
        }
    
    application {
        val windowState = rememberWindowState()

        LifecycleController(lifecycle, windowState)

        var isCloseRequested by remember { mutableStateOf(false) }
        
        Window(
            onCloseRequest =  {
                isCloseRequested=true
                stopKoin()
                              },
            state = windowState,
            title = "cms") {
            App(rootComponent)
            
                if (isCloseRequested) {
                    SaveStateDialog(
                        onSaveState = { saveStateToFile(stateKeeper.save()) },
                        onExitApplication = ::exitApplication,
                        onDismiss = { isCloseRequested = false },
                        )
                }
        }
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App(PreviewRootComponent())
}

@Preview()
@Composable
fun MainUiPreview() {
    AppTheme {
        MainUi(PreviewMainComponent())
    }
}

@Preview
@Composable
fun LandingUiPreview() {
    AppTheme {
        LandingUi(PreviewLandingComponent())
    }
}

@Preview
@Composable
fun SelfSignUpUiPreview() {
    AppTheme {
        SelfSignUpUi(PreviewSelfSignUpComponent())
    }
}

@Preview
@Composable
fun AdminSignUpUiPreview() {
    AppTheme {
        AdminSignUpUi(PreviewAdminSignUpComponent())
    }
}

@Preview
@Composable
fun SignInUiPreview() {
    AppTheme {
        SignInUi(PreviewSignInComponent())
    }
}

@Preview
@Composable
fun ResetPreview() {
    AppTheme {
        ResetUi(PreviewResetComponent())
    }
}

@Preview
@Composable
fun ProfilePreview() {
    AppTheme {
        ProfileUi(PreviewProfileComponent())
    }
}

@Preview
@Composable
fun HomeUiPreview() {
    AppTheme {
        HomeUi(PreviewHomeComponent())
    }
}

@Preview
@Composable
fun MapUiPreview() {
    AppTheme {
        MapUi(PreviewMapComponent())
    }
}

@Preview
@Composable
fun CMSPreview() {
    AppTheme {
        CMSUi(PreviewCMSComponent())
    }
}

@Preview
@Composable
fun QueuePreview() {
    AppTheme {
        QueueUi(PreviewQueueComponent())
    }
}

@Preview
@Composable
fun CDoxPreview() {
    AppTheme {
        CDoxUi(PreviewCDoxComponent())
    }
}

@Preview
@Composable
fun CDexPreview() {
    AppTheme {
        CDexUi(PreviewCDexComponent())
    }
}

@Preview
@Composable
fun DashboardUiPreview() {
    AppTheme {
        DashboardUi(PreviewDashboardComponent())
    }
}

@Preview
@Composable
fun SettingsUiPreview() {
    AppTheme {
        SettingsUi(PreviewSettingsComponent())
    }
}
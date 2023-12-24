package digital.sadad.project

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import core.di.initKoin
import org.jetbrains.compose.components.resources.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin
import ui.dashboard.DashboardUi
import ui.dashboard.PreviewDashboardComponent
import ui.root.DefaultRootComponent
import ui.dynamicfeatures.dynamicfeature.DefaultFeatureInstaller
import ui.home.HomeUi
import ui.home.PreviewHomeComponent
import ui.landing.LandingUi
import ui.landing.PreviewLandingComponent
import ui.main.MainUi
import ui.main.PreviewMainComponent
import ui.map.MapUi
import ui.map.PreviewMapComponent
import ui.reset.PreviewResetComponent
import ui.reset.ResetUi
import ui.root.PreviewRootComponent
import ui.settings.PreviewSettingsComponent
import ui.settings.SettingsUi
import ui.signin.PreviewSignInComponent
import ui.signin.SignInUi
import ui.selfsignup.PreviewSelfSignUpComponent
import ui.selfsignup.SignUpUi
import ui.theme.AppTheme

@OptIn(ExperimentalDecomposeApi::class)
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin(
            enableNetworkLogs = BuildConfig.DEBUG
        ) {
            androidContext(applicationContext)
        }
        
        val rootComponent =
            DefaultRootComponent(
                componentContext = defaultComponentContext(),
                storeFactory = DefaultStoreFactory(),
                featureInstaller = DefaultFeatureInstaller(context = this),
                )
        
        setContent {
            App(rootComponent=rootComponent)
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppAndroidPreview() {
    App(rootComponent=PreviewRootComponent())
}

@Preview(showSystemUi = true)
@Composable
fun MainUiPreview() {
    AppTheme {
        MainUi(PreviewMainComponent())
    }
}

@Preview(showSystemUi = true)
@Composable
fun LandingUiPreview() {
    AppTheme {
        LandingUi(PreviewLandingComponent())
    }
}

@Preview(showSystemUi = true)
@Composable
fun SignUpUiPreview() {
    AppTheme {
        SignUpUi(PreviewSelfSignUpComponent())
    }
}

@Preview(showSystemUi = true)
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

@Preview(showSystemUi = true)
@Composable
fun HomeUiPreview() {
    AppTheme {
        HomeUi(PreviewHomeComponent())
    }
}

@Preview(showSystemUi = true)
@Composable
fun MapUiPreview() {
    AppTheme {
        MapUi(PreviewMapComponent())
    }
}

@Preview(showSystemUi = true)
@Composable
fun DashboardUiPreview() {
    AppTheme {
        DashboardUi(PreviewDashboardComponent())
    }
}

@Preview(showSystemUi = true)
@Composable
fun SettingsUiPreview() {
    AppTheme {
        SettingsUi(PreviewSettingsComponent())
    }
}
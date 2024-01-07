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
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin
import ui.adminsignup.AdminSignUpUi
import ui.adminsignup.PreviewAdminSignUpComponent
import ui.cdex.CDexUi
import ui.cdex.PreviewCDexComponent
import ui.cdox.CDoxUi
import ui.cdox.PreviewCDoxComponent
import ui.cms.CMSUi
import ui.cms.PreviewCMSComponent
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
import ui.root.DefaultRootComponent
import ui.root.PreviewRootComponent
import ui.selfsignup.PreviewSelfSignUpComponent
import ui.selfsignup.SelfSignUpUi
import ui.settings.PreviewSettingsComponent
import ui.settings.SettingsUi
import ui.signin.PreviewSignInComponent
import ui.signin.SignInUi
import ui.theme.AppTheme

@OptIn(ExperimentalDecomposeApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin {
            androidContext(applicationContext)
        }

        val rootComponent =
            DefaultRootComponent(
                componentContext = defaultComponentContext(),
                storeFactory = DefaultStoreFactory(),
                featureInstaller = DefaultFeatureInstaller(context = this),
            )

        setContent {
            App(rootComponent)
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
    App(PreviewRootComponent())
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
fun SelfSignUpUiPreview() {
    AppTheme {
        SelfSignUpUi(PreviewSelfSignUpComponent())
    }
}

@Preview(showSystemUi = true)
@Composable
fun AdminSignUpUiPreview() {
    AppTheme {
        AdminSignUpUi(PreviewAdminSignUpComponent())
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

@Preview
@Composable
fun ProfilePreview() {
    AppTheme {
        ProfileUi(PreviewProfileComponent())
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
fun CMSPreview() {
    AppTheme {
        CMSUi(PreviewCMSComponent())
    }
}

@Preview(showSystemUi = true)
@Composable
fun QueuePreview() {
    AppTheme {
        QueueUi(PreviewQueueComponent())
    }
}

@Preview(showSystemUi = true)
@Composable
fun CDoxPreview() {
    AppTheme {
        CDoxUi(PreviewCDoxComponent())
    }
}

@Preview(showSystemUi = true)
@Composable
fun CDexPreview() {
    AppTheme {
        CDexUi(PreviewCDexComponent())
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
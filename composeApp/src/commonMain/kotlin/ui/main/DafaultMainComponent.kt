package ui.main

import WEB_PATH_ADMIN_SIGN_UP
import WEB_PATH_CDEX
import WEB_PATH_CDOX
import WEB_PATH_CMS
import WEB_PATH_DASHBOARD
import WEB_PATH_HOME
import WEB_PATH_MAP
import WEB_PATH_PROFILE
import WEB_PATH_QUEUE
import WEB_PATH_RESET
import WEB_PATH_SELF_SIGN_UP
import WEB_PATH_SETTINGS
import WEB_PATH_SIGN_IN
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import ui.adminsignup.DefaultAdminSignUpComponent
import ui.cdex.DefaultCDexComponent
import ui.cdox.DefaultCDoxComponent
import ui.cms.DefaultCMSComponent
import ui.dashboard.DefaultDashboardComponent
import ui.home.DefaultHomeComponent
import ui.common.component.navigation.model.DeepLink
import ui.main.MainComponent.Config
import ui.main.MainComponent.Child
import ui.main.MainComponent.Child.SelfSignUp
import ui.main.MainComponent.Child.AdminSignUp
import ui.main.MainComponent.Child.SignIn
import ui.main.MainComponent.Child.Reset
import ui.main.MainComponent.Child.Profile
import ui.main.MainComponent.Child.Home
import ui.main.MainComponent.Child.Map
import ui.main.MainComponent.Child.Dashboard
import ui.main.MainComponent.Child.Settings
import ui.main.MainComponent.Child.CMS
import ui.main.MainComponent.Child.Queue
import ui.main.MainComponent.Child.CDox
import ui.main.MainComponent.Child.CDex
import ui.map.DefaultMapComponent
import ui.profile.DefaultProfileComponent
import ui.queue.DefaultQueueComponent
import ui.reset.DefaultResetComponent
import ui.settings.DefaultSettingsComponent
import ui.signin.DefaultSignInComponent
import ui.selfsignup.DefaultSelfSignUpComponent

@OptIn(ExperimentalDecomposeApi::class)
class DefaultMainComponent(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    deepLink: DeepLink = DeepLink.None,
    webHistoryController: WebHistoryController? = null,
) : MainComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialStack = {
                getInitialStack(
                    webHistoryPaths = webHistoryController?.historyPaths,
                    deepLink = deepLink
                )
            },
            handleBackButton = true,
            childFactory = ::child
        )

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.SelfSignUp -> SelfSignUp(DefaultSelfSignUpComponent(componentContext, storeFactory))
            is Config.AdminSignUp -> AdminSignUp(DefaultAdminSignUpComponent(componentContext, storeFactory))
            is Config.SignIn -> SignIn(DefaultSignInComponent(componentContext, storeFactory, ::onSignInNavigate))
            is Config.Reset -> Reset(DefaultResetComponent(componentContext, storeFactory))
            is Config.Profile -> Profile(DefaultProfileComponent(componentContext, storeFactory))
            is Config.Home -> Home(DefaultHomeComponent(componentContext, storeFactory))
            is Config.Map -> Map(DefaultMapComponent(componentContext, storeFactory))
            is Config.CMS -> CMS(DefaultCMSComponent(componentContext, storeFactory))
            is Config.Queue -> Queue(DefaultQueueComponent(componentContext, storeFactory))
            is Config.CDox -> CDox(DefaultCDoxComponent(componentContext, storeFactory))
            is Config.CDex -> CDex(DefaultCDexComponent(componentContext, storeFactory))
            is Config.Dashboard -> Dashboard(DefaultDashboardComponent(componentContext, storeFactory))
            is Config.Settings -> Settings(DefaultSettingsComponent(componentContext, storeFactory))
        }

    init {
        webHistoryController?.attach(
            navigator = navigation,
            stack = stack,
            getPath = ::getPathForConfig,
            getConfiguration = ::getConfigForPath,
        )
    }

    override fun onNavigate(config: Config) = navigation.bringToFront(config)

    override fun onEvent(event: MainComponent.Event) = when (event) {
        MainComponent.Event.NAVIGATE_BACK -> navigation.pop()
    }

    private fun onSignInNavigate(config: Config): Unit = navigation.bringToFront(config)

    private companion object {
        private fun getInitialStack(webHistoryPaths: List<String>?, deepLink: DeepLink): List<Config> =
            webHistoryPaths
                ?.takeUnless(List<*>::isEmpty)
                ?.map(::getConfigForPath)
                ?: getInitialStack(deepLink)

        private fun getInitialStack(deepLink: DeepLink): List<Config> =
            when (deepLink) {
                is DeepLink.None -> listOf(DEFAULT_CONFIG)
                is DeepLink.Web -> listOf(getConfigForPath(deepLink.path))
            }

        private val pathConfigMap = mapOf(
            WEB_PATH_SELF_SIGN_UP to Config.SelfSignUp,
            WEB_PATH_ADMIN_SIGN_UP to Config.AdminSignUp,
            WEB_PATH_SIGN_IN to Config.SignIn,
            WEB_PATH_RESET to Config.Reset,
            WEB_PATH_PROFILE to Config.Profile,
            WEB_PATH_HOME to Config.Home,
            WEB_PATH_MAP to Config.Map,
            WEB_PATH_CMS to Config.CMS,
            WEB_PATH_QUEUE to Config.Queue,
            WEB_PATH_CDOX to Config.CDox,
            WEB_PATH_CDEX to Config.CDex,
            WEB_PATH_DASHBOARD to Config.Settings,
            WEB_PATH_SETTINGS to Config.Settings,
        )

        private fun getPathForConfig(config: Config): String =
            "/${pathConfigMap.entries.filter { it.value == config }.map { it.key }.single()}"

        private fun getConfigForPath(path: String): Config = pathConfigMap[path]!!
    }
}
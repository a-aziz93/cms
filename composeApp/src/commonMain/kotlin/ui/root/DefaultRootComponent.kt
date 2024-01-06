package ui.root

import WEB_PATH_LANDING
import WEB_PATH_MAIN
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import ui.dynamicfeatures.dynamicfeature.FeatureInstaller
import ui.landing.DefaultLandingComponent
import ui.main.DefaultMainComponent
import ui.common.component.navigation.model.DeepLink
import ui.root.RootComponent.Child
import ui.root.RootComponent.Child.Landing
import ui.root.RootComponent.Child.Main
import ui.root.RootComponent.Config

@OptIn(ExperimentalDecomposeApi::class)
class DefaultRootComponent(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val featureInstaller: FeatureInstaller,
    deepLink: DeepLink = DeepLink.None,
    webHistoryController: WebHistoryController? = null,
) : RootComponent, ComponentContext by componentContext {

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
            handleBackButton = false,
            childFactory = ::child
        )

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.Landing -> Landing(DefaultLandingComponent(componentContext, storeFactory, ::onLandingNavigate))
            is Config.Main -> Main(DefaultMainComponent(componentContext, storeFactory))
        }

    private fun onLandingNavigate(config: RootComponent.Config): Unit = navigation.bringToFront(config)

    init {
        webHistoryController?.attach(
            navigator = navigation,
            stack = stack,
            getPath = ::getPathForConfig,
            getConfiguration = ::getConfigForPath,
        )
    }

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
            WEB_PATH_LANDING to Config.Landing,
            WEB_PATH_MAIN to Config.Main,
        )

        private fun getPathForConfig(config: Config): String =
            "/${pathConfigMap.entries.filter { it.value == config }.map { it.key }.single()}"

        private fun getConfigForPath(path: String): Config = pathConfigMap[path]!!
    }

}
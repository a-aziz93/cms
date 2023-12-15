package ui.main

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import co.touchlab.kermit.Logger
import com.arkivanov.decompose.extensions.compose.stack.Children
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.*
import core.storage.KeyValueStorageImpl
import core.storage.StorageKeys
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.home.HomeUi
import ui.i18n.getCountryFlag
import ui.i18n.lngToCountryMap
import ui.main.component.AdaptiveNavigationLayout
import ui.theme.AppTheme
import kotlin.reflect.full.memberProperties
import ui.main.MainComponent.Child
import ui.main.MainComponent.Child.SignUp
import ui.main.MainComponent.Child.SignIn
import ui.main.MainComponent.Child.Reset
import ui.main.MainComponent.Child.Profile
import ui.main.MainComponent.Child.Home
import ui.main.MainComponent.Child.Map
import ui.main.MainComponent.Child.Dashboard
import ui.main.MainComponent.Child.Settings
import ui.map.MapUi
import ui.reset.ResetUi
import ui.settings.SettingsUi
import ui.signin.SignInUi
import ui.signup.SignUpUi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.backStack
import ui.i18n.Locales
import ui.i18n.listOfCountries
import ui.main.component.LocalePickerDialog
import ui.main.component.UserHead
import ui.model.NavigationItem
import ui.profile.ProfileUi
import cafe.adriel.lyricist.strings
import core.util.tabAnimation
import ui.dashboard.DashboardUi

@OptIn(ExperimentalResourceApi::class,ExperimentalMaterial3Api::class,ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
internal fun MainUi(component: MainComponent) {
    
    // Set current Koin instance to Compose context
    //    KoinContext {
    val keyValueStorage=KeyValueStorageImpl()

    val isDarkTheme=keyValueStorage.get(StorageKeys.IS_DARK_THEME.key,Boolean::class, isSystemInDarkTheme())

    var darkTheme by remember { mutableStateOf(isDarkTheme) }

    AppTheme(darkTheme=darkTheme) {

        val lyricist = rememberStrings(currentLanguageTag=keyValueStorage.get(StorageKeys.LANGUAGE.key,String::class, Locale.current.toLanguageTag()))

        ProvideStrings(lyricist) {
            val currentLngCountryCode = lngToCountryMap[lyricist.languageTag.split("-")[0]]!!

            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
            val topAppBarElementColor = if (scrollBehavior.state.collapsedFraction > 0.5) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onPrimary
            }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

                val  windowSizeClass= calculateWindowSizeClass()

                val navDrawerState = rememberDrawerState(initialValue =if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) DrawerValue.Closed else DrawerValue.Open)
                val scope = rememberCoroutineScope()

                var openLocaleDialog by remember { mutableStateOf(false) }
                
                val navigationItems= NavigationItems()
                    val selectedNavigationItemIndex = remember { mutableIntStateOf(getActiveNavigationItemIntex(navigationItems,component.childStack.active.configuration as MainComponent.Config)) }
                
                val profileTitle=strings.profile
               
                var title by remember { mutableStateOf("") }
                
                AdaptiveNavigationLayout(
                    layoutType = windowSizeClass.widthSizeClass,
                    drawerState=navDrawerState,
                    userHead = {
                               UserHead(
                                   id="SomeId",
                                   firstName="Aziz",
                                   lastName="Atoev",
                                   role="User",
                                   onClick={
                                       component.onOutput(MainComponent.Output.NavigateToProfile)
                                       title= profileTitle
                                       selectedNavigationItemIndex.intValue=-1
                                   }
                               )
                               },
                    userHeadProvided = true,
                    items=navigationItems,
                    selectedItemIndex=selectedNavigationItemIndex.intValue,
                    onNavigate = {index->
                        selectedNavigationItemIndex.intValue=index
                        component.onOutput(navConfigOutputMapper[navigationItems[selectedNavigationItemIndex.intValue].route!!]!!)
                    }
                ){
                    
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title={
                                    Text(text=if(selectedNavigationItemIndex.intValue>-1)navigationItems[selectedNavigationItemIndex.intValue].title else title )
                                      },
                                navigationIcon = {
                                    Row { 
                                        IconButton(onClick = {
                                            scope.launch {
                                                if(navDrawerState.isClosed){
                                                    navDrawerState.open()
                                                }else{
                                                    navDrawerState.close()
                                                }
                                            }
                                        }) {
                                            Icon(
                                                imageVector = EvaIcons.Outline.Menu2,
                                                contentDescription = "Menu"
                                            )
                                        }
                                    }
                                                 },
                                actions = {
                                    if(component.childStack.backStack.isNotEmpty()){
                                        IconButton(onClick = {
                                            component.onOutput(MainComponent.Output.NavigateBack)
                                            selectedNavigationItemIndex.intValue = getActiveNavigationItemIntex(navigationItems,component.childStack.active.configuration as MainComponent.Config)
                                        }) {
                                            Icon(
                                                imageVector = EvaIcons.Outline.ArrowheadLeft,
                                                contentDescription = "Back navigation"
                                            )
                                        }
                                    }
                                    IconButton(
                                        onClick = {
                                            openLocaleDialog=true
                                        }) {
                                        Image(modifier=Modifier.size(30.dp), painter = painterResource(getCountryFlag(currentLngCountryCode)), contentDescription = null,)
                                    }
                                    IconButton(onClick = {
                                        darkTheme=!darkTheme
                                        keyValueStorage.set(StorageKeys.IS_DARK_THEME.key,darkTheme)
                                        Logger.v("Picked ${if(darkTheme) "dark" else "light"} theme")
                                    }) {
                                        Icon(if(darkTheme)EvaIcons.Outline.Sun else EvaIcons.Outline.Moon, null)
                                    }
                                    IconButton(onClick = {
                                        Logger.v("Clicked SignOut")
                                    }) {
                                        Icon(EvaIcons.Outline.LogOut,null)
                                    }
                                          },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                                    navigationIconContentColor = topAppBarElementColor,
                                    titleContentColor = topAppBarElementColor,
                                    actionIconContentColor= topAppBarElementColor,
                                    ),
                                )
                                 },
                        ) {innerPadding->
                        if(openLocaleDialog){
                            LocalePickerDialog(
                                countries=Locales::class.memberProperties.map{lg-> listOfCountries.find { it.countryCode==lngToCountryMap[lg.call()] }!!},
                                defaultSelectedCountry = listOfCountries.find{it.countryCode==currentLngCountryCode}!!,
                                pickedCountry={c->
                                    lyricist.languageTag= lngToCountryMap.entries.find{ it.value==c.countryCode}!!.key
                                    openLocaleDialog=false
                                              },
                                onDismissRequest={
                                    openLocaleDialog=false
                                                 },
                                )
                        }
                        Box(modifier=Modifier.fillMaxSize().padding(innerPadding)){
                            Children(component=component)
                        }
                    }
                }
            }
        }
    }
//    }
    
}

private fun getActiveNavigationItemIntex(navigationItems:List<NavigationItem>,activeConfig:MainComponent.Config)=navigationItems.indexOfFirst { it.route==activeConfig }


@Composable
private fun Children(component: MainComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,

        // Workaround for https://issuetracker.google.com/issues/270656235
//        animation = stackAnimation(fade()),
                    animation = tabAnimation {
                        when (it) {
                            is SignUp -> 0
                            is SignIn -> 1
                            is Reset -> 2
                            is Profile -> 3
                            is Home -> 4
                            is Map -> 5
                            is Dashboard -> 6
                            is Settings -> 7
                        }
                                             },
        ) {
         when(val child = it.instance) {
                                    is SignUp -> SignUpUi(child.component)
                                    is SignIn -> SignInUi(child.component)
                                    is Reset -> ResetUi(child.component)
                                    is Profile -> ProfileUi(child.component)
                                    is Home -> HomeUi(child.component)
                                    is Map -> MapUi(child.component)
                                    is Dashboard -> DashboardUi(child.component)
                                    is Settings -> SettingsUi(child.component)
                                }
    }
}





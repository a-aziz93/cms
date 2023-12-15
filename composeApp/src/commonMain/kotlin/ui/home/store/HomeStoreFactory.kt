package ui.home.store


import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import core.model.cms.CMS
import core.model.cms.CMSSearchFilter
import data.cms.CMSRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import coroutineDispatchers

internal class CMSStoreFactory(
    private val storeFactory: StoreFactory,
    private val searchFilter: CMSSearchFilter,
    ): KoinComponent {

        private val cmsRepository by inject<CMSRepository>()

    fun create(): CMSStore =
        object : CMSStore, Store<CMSStore.Intent, CMSStore.State, Nothing> by storeFactory.create(
            name = "CMSStore",
            initialState = CMSStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Msg {
        data object CMSListLoading : Msg()
        data class CMSListLoaded(val cmsList: List<CMS>) : Msg()
        data class CMSListFailed(val error: String?) : Msg()
        data class SearchFilterUpdated(val searchFilter: CMSSearchFilter) : Msg()
        data object LastPageLoaded : Msg()
    }

    private inner class ExecutorImpl : CoroutineExecutor<CMSStore.Intent, Unit, CMSStore.State, Msg, Nothing>(
        coroutineDispatchers.main) {
        
        override fun executeAction(action: Unit) {
            loadCMSListByPage(page = 0)
        }
        
        override fun executeIntent(intent: CMSStore.Intent): Unit =
            when (intent) {
                is CMSStore.Intent.LoadCMSListByPage -> loadCMSListByPage(intent.page, state().isLastPageLoaded)
                is CMSStore.Intent.UpdateSearchFilter -> dispatch(Msg.SearchFilterUpdated(intent.searchFilter))
            }

        private var loadCMSListByPageJob: Job? = null
        private fun loadCMSListByPage(
            page: Long,
            isLastPageLoaded: Boolean = false
        ) {
            if (loadCMSListByPageJob?.isActive == true) return
            if (isLastPageLoaded) return

            loadCMSListByPageJob = scope.launch {
                dispatch(Msg.CMSListLoading)

//                cmsRepository
//                    .getCMSList(page)
//                    .onSuccess { cmsList ->
//                        if (cmsList.isEmpty()) {
//                            dispatch(Msg.LastPageLoaded)
//                        } else {
//                            dispatch(Msg.CMSListLoaded(cmsList))
//                        }
//                    }
//                    .onFailure { e ->
//                        dispatch(Msg.CMSListFailed(e.message))
//                    }
            }
        }
    }

    private object ReducerImpl: Reducer<CMSStore.State, Msg> {
        override fun CMSStore.State.reduce(msg: Msg): CMSStore.State =
            when (msg) {
                is Msg.CMSListLoading -> copy(isLoading = true)
                is Msg.CMSListLoaded -> CMSStore.State(cmsList = cmsList + msg.cmsList)
                is Msg.CMSListFailed -> copy(error = msg.error)
                is Msg.SearchFilterUpdated -> copy(searchFilter = msg.searchFilter)
                Msg.LastPageLoaded -> copy(isLastPageLoaded = true)
            }
    }
    }
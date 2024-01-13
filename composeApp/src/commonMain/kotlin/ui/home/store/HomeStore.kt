package ui.home.store

import com.arkivanov.mvikotlin.core.store.Store
import ui.cms.model.cms.CMS
import ui.cms.model.cms.CMSSearchFilter

interface CMSStore: Store<CMSStore.Intent, CMSStore.State, Nothing> {

    sealed class Intent {
        data class LoadCMSListByPage(val page: Long): Intent()
        data class UpdateSearchFilter(val searchFilter: CMSSearchFilter): Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val isLastPageLoaded: Boolean = false,
        val error: String? = null,
        val cmsList: List<CMS> = emptyList(),
        val searchFilter: CMSSearchFilter?=null,
        )
}
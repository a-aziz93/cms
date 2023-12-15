package core.network.di

import core.network.client.authorization.AuthorizationClient
import core.network.client.cms.CMSClient
import core.network.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module get() = { enableLogging ->
    module {
        single { createHttpClient(enableLogging) }
        single { AuthorizationClient(httpClient = get()) }
        single { CMSClient(httpClient = get()) }
    }
}
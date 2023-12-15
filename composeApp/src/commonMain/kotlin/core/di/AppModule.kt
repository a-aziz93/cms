package core.di

import core.database.di.databaseModule
import core.storage.di.storageModule
import data.di.dataModule
import core.network.di.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            storageModule,
            databaseModule,
            networkModule(enableNetworkLogs),
            dataModule
        )
    }
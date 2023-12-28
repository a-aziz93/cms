package core.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.logger.slf4jLogger
import org.koin.ksp.generated.*

expect fun platformModule(): Module

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        slf4jLogger()
        appDeclaration()
        modules(
            defaultModule(),
            platformModule(),
        )
    }

// Called by iOS etc
fun initKoin() = initKoin(enableNetworkLogs = false) {}

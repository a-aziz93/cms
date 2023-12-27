package core.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.logger.slf4jLogger
import org.koin.ksp.generated.*

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        slf4jLogger()
        appDeclaration()
        defaultModule()
    }

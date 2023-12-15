package core.database.di

import core.database.sqlDriverFactory
import org.koin.dsl.module

val databaseModule = module {
//    factory { sqlDriverFactory() }
//    single { createSomeDatabase(driver = get()) }
}
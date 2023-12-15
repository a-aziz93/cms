package core.storage.di

import core.storage.KeyValueStorage
import core.storage.KeyValueStorageImpl
import org.koin.dsl.module

val storageModule = module {
    single<KeyValueStorage> { KeyValueStorageImpl() }
}
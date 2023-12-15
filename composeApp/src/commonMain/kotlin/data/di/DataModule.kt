package data.di

import data.authorization.AuthorizationRepositoryImpl
import data.authorization.AuthorizationRepository
import data.authorization.AuthorizationService
import data.cms.CMSRepository
import data.cms.CMSRepositoryImpl
import data.cms.CMSService
import data.registration.RegistrationRepository
import data.registration.RegistrationRepositoryImpl
import data.registration.RegistrationService
import org.koin.dsl.module

val serviceModule=module{
    single<RegistrationService> { RegistrationService() }
    single<AuthorizationService> { AuthorizationService() }
    single<CMSService> { CMSService() }
}

val repositryModule = module {
    single<RegistrationRepository> { RegistrationRepositoryImpl() }
    single<AuthorizationRepository> { AuthorizationRepositoryImpl() }
    single<CMSRepository> { CMSRepositoryImpl() }
}

val dataModule=module{
    includes(serviceModule, repositryModule)
}
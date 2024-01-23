package digital.sadad.project.core.plugin.di.module.security

import digital.sadad.project.auth.service.security.basic.BasicAuthService
import digital.sadad.project.auth.service.security.digest.DigestAuthService
import digital.sadad.project.core.config.model.plugin.security.SecurityConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun securityModule(config: digital.sadad.project.core.config.model.plugin.security.SecurityConfig) = module {
    config.basic?.keys?.forEach { name ->
        single<BasicAuthService>(named(name)) { BasicAuthService(get(named(name))) }
    }
    config.digest?.keys?.forEach { name ->
        single<DigestAuthService>(named(name)) { DigestAuthService(get(named(name))) }
    }
}
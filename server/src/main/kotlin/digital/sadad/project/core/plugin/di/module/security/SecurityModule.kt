package digital.sadad.project.core.plugin.di.module.security

import digital.sadad.project.auth.service.security.basic.BasicAuthService
import digital.sadad.project.auth.service.security.digest.DigestAuthService
import digital.sadad.project.core.config.model.plugin.security.SecurityConfig
import digital.sadad.project.core.plugin.di.module.keycloak.keycloakModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun securityModule(config: SecurityConfig) = module {
    config.basic?.keys?.forEach { name ->
        single<BasicAuthService>(named(name)) { BasicAuthService(get(named(name))) }
    }
    config.digest?.keys?.forEach { name ->
        single<DigestAuthService>(named(name)) { DigestAuthService(get(named(name))) }
    }

    // Keycloak module with clients
    config.oauth?.let {
        keycloakModule(it.entries.filter { it.value.provider.name == "keycloak" && it.value.client != null }
            .associate { it.key to it.value.client!! })
    }
}
package digital.sadad.project.core.plugin.di.module.security

import digital.sadad.project.core.config.model.plugin.security.SecurityConfig
import digital.sadad.project.core.plugin.di.module.keycloak.keycloakModule
import digital.sadad.project.core.security.service.basic.BasicAuthService
import digital.sadad.project.core.service.security.digest.DigestAuthService
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun securityModule(config: SecurityConfig) = module {
    // BASIC
    config.basic?.forEach { (name, config) ->
        single<BasicAuthService>(named(name)) {
            BasicAuthService(
                config,
                get(named(name))
            )
        }
    }

    // DIGEST
    config.digest?.forEach { (name, config) ->
        single<DigestAuthService>(named(name)) { DigestAuthService(config, get(named(name))) }
    }

    // KEYCLOAK CLIENTS
    config.oauth?.let {
        keycloakModule(it.entries.filter { it.value.server.name == "keycloak" && it.value.client != null }
            .associate { it.key to it.value.client!! })
    }
}
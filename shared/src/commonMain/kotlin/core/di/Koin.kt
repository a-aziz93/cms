package core.di

import org.koin.core.annotation.KoinInternalApi
import org.koin.core.definition.Kind
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent
import kotlin.reflect.full.isSubclassOf

@OptIn(KoinInternalApi::class)
inline fun <reified T : Any> getAll(): Map<String, T> = KoinJavaComponent.getKoin().instanceRegistry
    .instances.entries
    .filter { it.value.beanDefinition.kind == Kind.Singleton }
    .filter { it.value.beanDefinition.primaryType.isSubclassOf(T::class) }
    .associate {
        it.key to KoinJavaComponent.getKoin().get(
            clazz = it.value.beanDefinition.primaryType,
            qualifier = named(it.key),
            parameters = null
        )
    }
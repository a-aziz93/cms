package core.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlin.reflect.KClass

interface KeyValueStorage {
    fun set(key:String,value:Any)
    
    fun <T: Any> get(key:String,kClass:KClass<T>):T?
    
    fun <T: Any> get(key:String,kClass:KClass<T>,defaultValue:T):T
    
    fun <T> set(serializer: KSerializer<T>, key: String, value: T, serializersModule: SerializersModule): Unit
    
    fun <T> get(serializer: KSerializer<T>, key: String, serializersModule: SerializersModule): T?

    fun <T> get(serializer:KSerializer<T>, key: String, defaultValue: T, serializersModule: SerializersModule): T
   
    fun remove(key:String): Unit
   
    fun cleanStorage()
}
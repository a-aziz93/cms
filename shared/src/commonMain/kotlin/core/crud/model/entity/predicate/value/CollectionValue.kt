package core.crud.model.entity.predicate.value

import core.crud.model.entity.predicate.CollectionVariable

interface CollectionValue<T : Any> : Value<Collection<T>>, CollectionVariable
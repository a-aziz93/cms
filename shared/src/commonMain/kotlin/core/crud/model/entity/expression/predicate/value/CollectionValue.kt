package core.crud.model.entity.expression.predicate.value

import core.crud.model.entity.expression.predicate.NumberCollectionVariable

interface CollectionValue<T : Any> : Value<Collection<T>>
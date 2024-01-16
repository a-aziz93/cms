package core.crud.model.entity.predicate.operation

import kotlinx.serialization.Serializable

@Serializable
enum class LogicalOperation : PredicateOperation {
    AND,
    OR,
    NOT,
}
package core.crud.model.predicate.operation

import kotlinx.serialization.Serializable

@Serializable
enum class LogicalOperation : PredicateOperation {
    AND,
    OR,
    NOT,
}
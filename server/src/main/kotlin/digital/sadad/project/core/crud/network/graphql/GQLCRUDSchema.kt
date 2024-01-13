package digital.sadad.project.core.crud.network.graphql

import com.apurebase.kgraphql.GraphQL
import core.crud.model.predicate.operation.Predicate

inline fun <reified T : Any> GraphQL.Configuration.crudSchema() {
    schema {
        type<T>()
        query()
        inputType<Predicate>()
        mutation()
    }
}
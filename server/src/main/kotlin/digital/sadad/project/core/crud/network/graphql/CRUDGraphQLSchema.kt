package digital.sadad.project.core.crud.network.graphql

import com.apurebase.kgraphql.GraphQL
import core.crud.service.CRUDService

inline fun <reified T : Any> GraphQL.Configuration.crudGraphQL(crudService: CRUDService<T>) {
    schema {
        type<T>()
        query()
        inputType<Predicate>()
        mutation()
    }
}
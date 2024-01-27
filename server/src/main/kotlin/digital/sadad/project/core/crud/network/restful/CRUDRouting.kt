package digital.sadad.project.core.crud.network.restful

import core.crud.service.CRUDService
import io.ktor.server.application.*

inline fun <reified T : Any> Application.crudRouting(path: String, crudService: CRUDService<T>) {

}
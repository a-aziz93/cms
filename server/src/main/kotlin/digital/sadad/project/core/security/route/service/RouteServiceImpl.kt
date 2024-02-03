package digital.sadad.project.core.security.route.service

import digital.sadad.project.core.security.route.repository.RouteCRUDRepository

class RouteServiceImpl(
    override val crudRepository: RouteCRUDRepository,
) : RouteService {
}
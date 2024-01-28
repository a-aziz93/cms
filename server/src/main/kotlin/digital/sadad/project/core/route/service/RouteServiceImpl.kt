package digital.sadad.project.core.route.service

import digital.sadad.project.core.route.repository.RouteCRUDRepository

class RouteServiceImpl(
    override val crudRepository: RouteCRUDRepository,
) : RouteService {
}
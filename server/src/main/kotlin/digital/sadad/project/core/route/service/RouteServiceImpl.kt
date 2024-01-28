package digital.sadad.project.core.route.service

import digital.sadad.project.core.route.repository.RouteRepository

class RouteServiceImpl(
    override val crudRepository: RouteRepository,
) : RouteService {
}
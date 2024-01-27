package digital.sadad.project.core.service.route

import core.crud.model.route.entity.RouteEntity
import core.crud.repository.CRUDRepository
import digital.sadad.project.core.repository.route.RouteRepository
import digital.sadad.project.core.service.routerole.RouteRoleService

class RouteServiceImpl(
    override val crudRepository: RouteRepository,
) : RouteService {
}
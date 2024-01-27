package digital.sadad.project.core.service.routerole

import core.crud.model.route.entity.RouteEntity
import core.crud.model.routerole.entity.RouteRoleEntity
import core.crud.repository.CRUDRepository
import digital.sadad.project.core.repository.routerole.RouteRoleRepository
import digital.sadad.project.core.service.routerole.RouteRoleService

class RouteRoleServiceImpl(
    override val crudRepository: RouteRoleRepository,
) : RouteRoleService {
}
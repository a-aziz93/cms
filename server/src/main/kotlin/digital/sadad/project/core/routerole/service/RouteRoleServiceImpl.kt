package digital.sadad.project.core.routerole.service

import digital.sadad.project.core.routerole.repository.RouteRoleRepository

class RouteRoleServiceImpl(
    override val crudRepository: RouteRoleRepository,
) : RouteRoleService {
}
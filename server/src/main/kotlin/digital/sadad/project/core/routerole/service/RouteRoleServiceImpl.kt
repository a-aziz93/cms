package digital.sadad.project.core.routerole.service

import digital.sadad.project.core.routerole.repository.RouteRoleCRUDRepository

class RouteRoleServiceImpl(
    override val crudRepository: RouteRoleCRUDRepository,
) : RouteRoleService {
}
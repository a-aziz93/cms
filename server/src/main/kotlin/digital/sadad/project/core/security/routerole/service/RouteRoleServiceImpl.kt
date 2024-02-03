package digital.sadad.project.core.security.routerole.service

import digital.sadad.project.core.security.routerole.repository.RouteRoleCRUDRepository

class RouteRoleServiceImpl(
    override val crudRepository: RouteRoleCRUDRepository,
) : RouteRoleService {
}
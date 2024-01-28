package digital.sadad.project.core.security.service.keycloak

import core.role.model.Role
import core.crud.service.CRUDService
import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.RoleRepresentation

class KeycloakRoleService(
    val client: Keycloak,
    val realm: String,
) : CRUDService<Role> {
    fun create(name: String) {
        val role = RoleRepresentation()
        role.name = name
        client
            .realm(realm)
            .roles()
            .create(role)
    }

    fun findAll(): List<RoleRepresentation> =
        client
            .realm(realm)
            .roles()
            .list()

    fun findByName(roleName: String): RoleRepresentation =
        client
            .realm(realm)
            .roles()
            .get(roleName)
            .toRepresentation()
}
package digital.sadad.project.core.service.security.keycloak

import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.RoleRepresentation

class KeycloakRoleService(
    val client: Keycloak,
    val realm: String,
) {
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
package digital.sadad.project.auth.service.keycloak

import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.GroupRepresentation

class KeycloakGroupService(
    val client: Keycloak,
    val realm: String,
) {
    fun create(name: String) {
        val group = GroupRepresentation()
        group.name = name
        client
            .realm(realm)
            .groups()
            .add(group)
    }

    fun findAll(): List<GroupRepresentation> =
        client
            .realm(realm)
            .groups()
            .groups()
}
package digital.sadad.project.auth.service.keycloak

import digital.sadad.project.auth.entity.user.UserEntity
import jakarta.ws.rs.core.Response
import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.RoleRepresentation
import org.keycloak.representations.idm.UserRepresentation

class KeycloakUserService(
    val client: Keycloak,
    val realm: String,
) {
    fun findAll(): List<UserRepresentation> =
        client
            .realm(realm)
            .users()
            .list()

    fun findByUsername(username: String): List<UserRepresentation> =
        client
            .realm(realm)
            .users()
            .search(username)

    fun findById(id: String): UserRepresentation =
        client
            .realm(realm)
            .users()
            .get(id)
            .toRepresentation()

    fun assignToGroup(userId: String, groupId: String) {
        client
            .realm(realm)
            .users()
            .get(userId)
            .joinGroup(groupId)
    }

    fun assignRole(userId: String, roleRepresentation: RoleRepresentation) {
        client
            .realm(realm)
            .users()
            .get(userId)
            .roles()
            .realmLevel()
            .add(listOf(roleRepresentation))
    }

    fun create(request: UserEntity): Response {
        val password = preparePasswordRepresentation(request.password)
        val user = prepareUserRepresentation(request, password)
        return client
            .realm(realm)
            .users()
            .create(user)
    }

    private fun preparePasswordRepresentation(
        password: String
    ): CredentialRepresentation {
        val cR = CredentialRepresentation()
        cR.isTemporary = false
        cR.type = CredentialRepresentation.PASSWORD
        cR.value = password
        return cR
    }

    private fun prepareUserRepresentation(
        request: UserEntity,
        cR: CredentialRepresentation
    ): UserRepresentation {
        val newUser = UserRepresentation()
        newUser.username = request.username
        newUser.credentials = listOf(cR)
        newUser.isEnabled = true
        return newUser
    }
}
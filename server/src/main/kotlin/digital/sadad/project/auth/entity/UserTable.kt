package digital.sadad.project.auth.entity

import org.ufoss.kotysa.GenericTable

object UserTable : GenericTable<UserEntity>("users") {
    // Primary key
    val id = bigInt(UserEntity::id).identity().primaryKey("PK_users")

    // Other fields
    val name = varchar(UserEntity::name)
    val email = varchar(UserEntity::email)
    val username = varchar(UserEntity::username).unique()
    val password = varchar(UserEntity::password)
    val roleId = uuid(UserEntity::roleId)
        .foreignKey(RoleTable.id, "FK_users_roles")
    val avatar = varchar(UserEntity::avatar)
    val active = boolean(UserEntity::active)

    // Metadata
    val createdBy = varchar(UserEntity::createdBy, "created_by")
    val createdAt = timestamp(UserEntity::createdAt, "created_at")
    val updatedBy = varchar(UserEntity::updatedBy, "updated_by")
    val updatedAt = timestamp(UserEntity::updatedAt, "updated_at")
}
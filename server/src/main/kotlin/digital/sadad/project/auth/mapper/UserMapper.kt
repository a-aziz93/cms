package digital.sadad.project.auth.mapper

import auth.dto.UserCreateDto
import auth.dto.UserDto
import digital.sadad.project.auth.entity.UserEntity

fun UserEntity.toDto(roles: List<String>): UserDto = UserDto(
    id = this.id,
    name = this.name,
    email = this.email,
    username = this.username,
    roles = roles,
    avatar = this.avatar,
    createdBy = this.createdBy,
    createdAt = this.createdAt.toString(),
    updatedBy = this.updatedBy,
    updatedAt = this.updatedAt.toString(),
    active = this.active
)

fun UserCreateDto.toModel(): UserEntity {
    return User(
        name = this.name,
        email = this.email,
        username = this.username,
        password = this.password,
        avatar = this.avatar ?: User.DEFAULT_IMAGE,
        role = this.role ?: User.Role.USER
    )
}





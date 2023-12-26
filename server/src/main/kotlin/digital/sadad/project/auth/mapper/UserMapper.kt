package digital.sadad.project.auth.mapper

import digital.sadad.project.auth.dto.UserCreateDto
import digital.sadad.project.auth.dto.UserDto
import digital.sadad.project.auth.model.User

fun User.toDto(): UserDto {
    return UserDto(
        id = this.id,
        name = this.name,
        email = this.email,
        username = this.username,
        avatar = this.avatar,
        role = this.role,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
        isDeleted = this.deleted
    )
}

fun UserCreateDto.toModel(): User {
    return User(
        name = this.name,
        email = this.email,
        username = this.username,
        password = this.password,
        avatar = this.avatar ?: User.DEFAULT_IMAGE,
        role = this.role ?: User.Role.USER
    )
}





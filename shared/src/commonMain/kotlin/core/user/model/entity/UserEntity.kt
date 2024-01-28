package core.user.model.entity

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import core.user.model.User
import core.user.model.dto.UserCreateDto
import core.user.model.dto.UserDto
import java.time.LocalDateTime

@KConMapper(
    toClasses = [UserCreateDto::class, UserDto::class],
    fromClasses = [UserCreateDto::class, UserDto::class],
)
class UserEntity(
    val id: Long? = null,
    name: String,
    email: String,
    username: String,
    password: String,
    avatar: String? = null,
    active: Boolean = false,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : User(
    name,
    email,
    username,
    password,
    avatar,
    active,
)
package core.security.user.model.entity

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import core.security.user.model.User
import core.security.user.model.dto.UserDto
import java.time.LocalDateTime

@KConMapper(
    toClasses = [User::class, UserDto::class],
    fromClasses = [User::class, UserDto::class],
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
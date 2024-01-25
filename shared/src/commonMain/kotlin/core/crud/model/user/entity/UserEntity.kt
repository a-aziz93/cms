package core.crud.model.user.entity

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import core.crud.model.user.User
import core.crud.model.user.dto.UserCreateDto
import core.crud.model.user.dto.UserDto
import core.crud.model.user.dto.UserLoginDto
import core.crud.model.user.dto.UserUpdateDto
import java.time.LocalDateTime
import java.util.*

@KConMapper(
    fromClasses = [UserCreateDto::class, UserUpdateDto::class, UserLoginDto::class, UserDto::class],
    toClasses = [UserCreateDto::class, UserUpdateDto::class, UserLoginDto::class, UserDto::class]
)
class UserEntity(
    val id: Long?,
    name: String,
    email: String,
    username: String,
    password: String,
    avatar: String? = null,
    active: Boolean = false,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
) : User(
    name,
    email,
    username,
    password,
    avatar,
    active,
)
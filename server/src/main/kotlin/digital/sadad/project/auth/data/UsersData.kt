package digital.sadad.project.auth.data

import de.nycode.bcrypt.hash
import digital.sadad.project.auth.model.User


fun userData(): MutableMap<Long, User> = mutableMapOf(
    1L to User(
        id = 1L,
        name = "Admin",
        username = "Admin",
        email = "admin@gmail.com",
        password = hash("admin", 12).decodeToString(),
        avatar = User.DEFAULT_IMAGE,
        role = User.Role.ADMIN
    ),
)

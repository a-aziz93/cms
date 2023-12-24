package digital.sadad.project.user.data

import de.nycode.bcrypt.hash
import digital.sadad.project.user.model.User


fun userDemoData(): MutableMap<Long, User> = mutableMapOf(
    1L to User(
        id = 1L,
        name = "Admin",
        username = "Admin",
        email = "admin@gmail.com",
        password = hash("admin", 12).decodeToString(),
        avatar = User.DEFAULT_IMAGE,
        role = User.Role.ADMIN
    ),
    2L to User(
        id = 2L,
        name = "Ana Lopez",
        username = "ana",
        email = "ana@lopez.com",
        password = hash("ana1234", 12).decodeToString(),
        avatar = User.DEFAULT_IMAGE,
        role = User.Role.USER
    )
)

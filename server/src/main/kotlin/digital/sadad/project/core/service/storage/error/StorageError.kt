package digital.sadad.project.core.service.storage.error

import core.error.Error

class SaveError(message: String) : Error(message)
class NotFoundError(message: String) : Error(message)
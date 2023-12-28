package digital.sadad.project.core.storage.error

import digital.sadad.project.core.error.IOError

class SaveError(message: String) : IOError(message)
class NotFoundError(message: String) : IOError(message)
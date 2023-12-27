package core.util

import com.arkivanov.essenty.statekeeper.SerializableContainer
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import javax.swing.SwingUtilities

internal fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}

private const val SAVED_STATE_FILE_NAME = "saved_state.dat"

fun saveStateToFile(state: SerializableContainer) {
    ObjectOutputStream(File(SAVED_STATE_FILE_NAME).outputStream()).use { output ->
        output.writeObject(state)
    }
}

fun tryRestoreStateFromFile(): SerializableContainer? =
    File(SAVED_STATE_FILE_NAME).takeIf(File::exists)?.let { file ->
        try {
            ObjectInputStream(file.inputStream()).use(ObjectInputStream::readObject) as SerializableContainer
        } catch (e: Exception) {
            null
        } finally {
            file.delete()
        }
    }
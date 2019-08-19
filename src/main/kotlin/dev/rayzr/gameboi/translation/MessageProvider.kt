package dev.rayzr.gameboi.translation

import java.text.MessageFormat
import java.util.*

/**
 * A message provider using a given resource bundle.
 *
 * @param resourceBundle the underlying resource bundle
 */
class MessageProvider(private val resourceBundle: ResourceBundle) {

    /**
     * Translates a given key.
     *
     * @param key the key to translate
     * @param format the format objects
     */
    fun tr(key: String, vararg format: Any): String {
        return MessageFormat(resourceBundle.getString(key), resourceBundle.locale).format(format)
    }
}
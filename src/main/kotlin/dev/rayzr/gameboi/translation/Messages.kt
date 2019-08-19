package dev.rayzr.gameboi.translation

import java.util.*
import kotlin.collections.HashMap

/**
 * Provides access to all messages used by gameboi.
 */
object Messages {

    private val providerCache: MutableMap<Locale, MessageProvider> = HashMap()

    /**
     * Returns the provider for the given language.
     */
    fun getProvider(locale: Locale): MessageProvider {
        return providerCache.getOrPut(locale) {
            MessageProvider(ResourceBundle.getBundle("messages.Messages.yaml", locale))
        }
    }

    /**
     * Translates a string.
     *
     * @see MessageProvider.tr
     */
    fun tr(locale: Locale, key: String, vararg format: Any): String {
        return getProvider(locale).tr(key, format)
    }
}
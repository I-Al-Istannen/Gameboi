package dev.rayzr.gameboi.data.settings

import dev.rayzr.gameboi.Gameboi
import java.util.*

class GuildSettings(var guild: Long, var prefix: String? = null, var locale: Locale? = null) {
    val realPrefix get() = prefix ?: Gameboi.prefix
    val chosenLocale get() = locale ?: Locale.ENGLISH

    fun toMap(): Map<String, Any?> {
        return mapOf(
                "prefix" to prefix,
                "locale" to chosenLocale.toLanguageTag()
        )
    }
}

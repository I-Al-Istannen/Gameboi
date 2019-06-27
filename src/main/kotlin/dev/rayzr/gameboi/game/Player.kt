package dev.rayzr.gameboi.game

import dev.rayzr.gameboi.data.DataManager
import dev.rayzr.gameboi.data.PlayerData
import net.dv8tion.jda.api.entities.User

class Player private constructor(val user: User, var currentMatch: Match? = null) {
    companion object {
        // Cache
        // TODO: Clear cache at regular intervals for AFK players to avoid memory usage?
        private val players: MutableMap<Long, Player> = mutableMapOf()

        operator fun get(user: User) = players.computeIfAbsent(user.idLong) { Player(user) }
    }

    fun getData() = DataManager.getPlayerData(this)
    fun editData(action: PlayerData.() -> Unit) = DataManager.editPlayerData(this, action)

    override fun hashCode(): Int {
        return user.idLong.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (user.id != other.user.id) return false

        return true
    }
}
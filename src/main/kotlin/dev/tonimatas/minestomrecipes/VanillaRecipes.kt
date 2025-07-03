package dev.tonimatas.minestomrecipes

import net.minestom.server.MinecraftServer

object VanillaRecipes {
    private val logger = MinecraftServer.LOGGER

    fun init() {
        val count = 0

        logger.info("Loaded {} recipes.", count)
    }
}
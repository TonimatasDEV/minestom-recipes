package dev.tonimatas.test

import dev.tonimatas.minestomrecipes.VanillaRecipes
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.GameMode
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.event.player.PlayerPickBlockEvent
import net.minestom.server.instance.Instance
import net.minestom.server.instance.LightingChunk
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.GenerationUnit
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.utils.chunk.ChunkSupplier

class TestServer {
    init {
        val server = MinecraftServer.init()

        val instance: Instance = MinecraftServer.getInstanceManager().createInstanceContainer()
        instance.chunkSupplier = ChunkSupplier { inst, x, z -> LightingChunk(inst, x, z) }

        instance.setGenerator { unit: GenerationUnit ->
            unit.modifier().fillHeight(0, 65, Block.GRASS_BLOCK)
        }

        VanillaRecipes.init()

        MinecraftServer.getGlobalEventHandler()
            .addListener(AsyncPlayerConfigurationEvent::class.java) { event: AsyncPlayerConfigurationEvent ->
                event.spawningInstance = instance
                event.player.respawnPoint = Pos(0.0, 65.0, 0.0)
                event.player.setGameMode(GameMode.CREATIVE) 
            }
            .addListener(PlayerPickBlockEvent::class.java) { event: PlayerPickBlockEvent ->
                event.player.openInventory(Inventory(InventoryType.FURNACE, "Crafting Test"))
            }
            .addListener(PlayerBlockBreakEvent::class.java) { event: PlayerBlockBreakEvent ->
                if (event.player.gameMode == GameMode.CREATIVE) {
                    event.player.setGameMode(GameMode.SURVIVAL)
                } else {
                    event.player.setGameMode(GameMode.CREATIVE)
                }
            }

        server.start("0.0.0.0", 25565)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            TestServer()
        }
    }
}
package dev.tonimatas.minestomrecipes.util

import net.minestom.server.MinecraftServer
import net.minestom.server.item.Material
import net.minestom.server.recipe.Ingredient
import net.minestom.server.recipe.display.SlotDisplay

object IngredientUtils {
    fun fromSlotDisplay(slotDisplay: SlotDisplay): Ingredient? {
        return when (slotDisplay) {
            is SlotDisplay.Item -> Ingredient(slotDisplay.material)
            is SlotDisplay.Tag -> {
                val registry = Material.staticRegistry().getTag(slotDisplay.tag)
                val materials = mutableListOf<Material>()

                for (material in registry!!) {
                    val material = Material.fromKey(material.key())
                    materials.add(material!!)
                }

                return Ingredient(materials)
            }

            else -> {
                MinecraftServer.LOGGER.warn("Unknown slotDisplay type: ${slotDisplay.javaClass.simpleName}")
                null
            }
        }
    }
}
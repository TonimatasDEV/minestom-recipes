package dev.tonimatas.minestomrecipes.util

import net.minestom.server.item.Material
import net.minestom.server.recipe.display.SlotDisplay
import net.minestom.server.registry.TagKey

object SlotDisplayUtils {
    fun getSlotDisplayFromId(id: String): SlotDisplay {
        if (id.startsWith("#")) {
            val tag = TagKey.ofHash<Material>(id)
            return SlotDisplay.Tag(tag)
        } else {
            val material = Material.fromKey(id)!!
            return SlotDisplay.Item(material)
        }
    }
}
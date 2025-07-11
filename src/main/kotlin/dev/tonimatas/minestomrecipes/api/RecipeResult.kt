package dev.tonimatas.minestomrecipes.api

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class RecipeResult(val id: String, val count: Int?) {
    fun getResult(): ItemStack {
        val material = Material.fromKey(id)!!

        return if (count != null) {
            ItemStack.of(material, count)
        } else {
            ItemStack.of(material)
        }
    }
}
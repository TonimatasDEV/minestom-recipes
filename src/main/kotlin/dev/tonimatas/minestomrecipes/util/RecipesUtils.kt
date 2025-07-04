package dev.tonimatas.minestomrecipes.util

import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import net.minestom.server.item.Material
import net.minestom.server.recipe.display.SlotDisplay
import net.minestom.server.registry.TagKey

object RecipesUtils {
    val GSON = GsonBuilder().setPrettyPrinting().create()!!

    fun get(): JsonArray {
        val inputStream = RecipesUtils::class.java.getResourceAsStream("/recipes.json")
        
        if (inputStream == null) {
            throw RuntimeException("Could not load recipes.")
        }
        
        return GSON.fromJson(inputStream.bufferedReader(), JsonArray::class.java)
    }
    
    fun getIngredients(ingredients: List<String>): List<SlotDisplay> {
        val ingredientsList = mutableListOf<SlotDisplay>()
        
        for (id in ingredients) {
            if (id.startsWith("#")) {
                val tag = TagKey.ofHash<Material>(id)
                val slotDisplay = SlotDisplay.Tag(tag)
                ingredientsList.add(slotDisplay)
            } else {
                val material = Material.fromKey(id)
                
                if (material != null) {
                    val slotDisplay = SlotDisplay.Item(material)
                    ingredientsList.add(slotDisplay)
                }
            }
        }
        
        return ingredientsList
    }
}
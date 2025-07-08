package dev.tonimatas.minestomrecipes.types

import dev.tonimatas.minestomrecipes.api.RecipeResult
import dev.tonimatas.minestomrecipes.util.ExtraCodecs
import dev.tonimatas.minestomrecipes.util.IngredientUtils
import dev.tonimatas.minestomrecipes.util.SlotDisplayUtils
import net.minestom.server.codec.Codec
import net.minestom.server.codec.StructCodec
import net.minestom.server.item.Material
import net.minestom.server.recipe.Ingredient
import net.minestom.server.recipe.Recipe
import net.minestom.server.recipe.RecipeBookCategory
import net.minestom.server.recipe.display.RecipeDisplay
import net.minestom.server.recipe.display.SlotDisplay

class CraftingShapedRecipe(
    val key: Map<String, List<String>>,
    val pattern: List<String>,
    val result: RecipeResult,
    val group: String?,
    val category: RecipeBookCategory
) : Recipe {
    override fun createRecipeDisplays(): List<RecipeDisplay?> {
        return listOf(
            RecipeDisplay.CraftingShaped(
                pattern.first().count(),
                pattern.size,
                getIngredients(),
                SlotDisplay.ItemStack(result.getResult()),
                SlotDisplay.Item(Material.CRAFTING_TABLE)
            )
        )
    }

    override fun craftingRequirements(): List<Ingredient?>? {
        return getIngredients().map { IngredientUtils.fromSlotDisplay(it) }.toList()
    }

    override fun recipeBookCategory(): RecipeBookCategory? {
        return category
    }
    
    fun getIngredients(): List<SlotDisplay> {
        val ingredients = mutableListOf<SlotDisplay>()
        
        for (line in pattern) {
            for (char in line) {
                val keyStr = char.toString()
                
                if (keyStr == " ") {
                    ingredients.add(SlotDisplay.Empty.INSTANCE)
                } else {
                    val ids = key["$char"]!!

                    if (ids.size == 1) {
                        val id = ids.first()
                        ingredients.add(SlotDisplayUtils.getSlotDisplayFromId(id))
                    } else {
                        val displaySlots = mutableListOf<SlotDisplay>()

                        for (id in ids) {
                            displaySlots.add(SlotDisplayUtils.getSlotDisplayFromId(id))
                        }

                        ingredients.add(SlotDisplay.Composite(displaySlots))
                    }
                }
            }
        }
        return ingredients
    }

    override fun recipeBookGroup(): String? {
        return group
    }

    companion object {
        val CODEC: StructCodec<CraftingShapedRecipe> = StructCodec.struct(
            "key", ExtraCodecs.STRING_MAP.optional(), CraftingShapedRecipe::key,
            "pattern", Codec.STRING.list(), CraftingShapedRecipe::pattern,
            "result", ExtraCodecs.RESULT.optional(), CraftingShapedRecipe::result,
            "group", Codec.STRING.optional(), CraftingShapedRecipe::group,
            "category", RecipeBookCategory.CODEC.optional(), CraftingShapedRecipe::category,
            ::CraftingShapedRecipe
        )
    }
}
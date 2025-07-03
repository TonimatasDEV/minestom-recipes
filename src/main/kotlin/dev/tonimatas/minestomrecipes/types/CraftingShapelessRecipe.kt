package dev.tonimatas.minestomrecipes.types

import dev.tonimatas.minestomrecipes.api.RecipeResult
import dev.tonimatas.minestomrecipes.util.ExtraCodecs
import dev.tonimatas.minestomrecipes.util.IngredientUtils
import dev.tonimatas.minestomrecipes.util.RecipesUtils
import net.minestom.server.codec.Codec
import net.minestom.server.codec.StructCodec
import net.minestom.server.item.Material
import net.minestom.server.recipe.Ingredient
import net.minestom.server.recipe.Recipe
import net.minestom.server.recipe.RecipeBookCategory
import net.minestom.server.recipe.display.RecipeDisplay
import net.minestom.server.recipe.display.SlotDisplay


class CraftingShapelessRecipe(val group: String?, val category: String, val result: RecipeResult, val ingredients: List<String>, val type: String) : Recipe {
    override fun createRecipeDisplays(): List<RecipeDisplay?> {
        return listOf(
            RecipeDisplay.CraftingShapeless(
                RecipesUtils.getIngredients(ingredients),
                SlotDisplay.ItemStack(result.getResult()),
                SlotDisplay.Item(Material.CRAFTING_TABLE)
            )
        )
    }

    override fun craftingRequirements(): List<Ingredient?>? {
        val displaySlots = RecipesUtils.getIngredients(ingredients)
        return displaySlots.map { IngredientUtils.fromSlotDisplay(it) }.toList()
    }

    override fun recipeBookCategory(): RecipeBookCategory? {
        return RecipesUtils.getCategory(category)
    }

    override fun recipeBookGroup(): String? {
        return group
    }
    
    companion object {
        val CODEC: StructCodec<CraftingShapelessRecipe> = StructCodec.struct(
            "group", Codec.STRING.optional(), CraftingShapelessRecipe::group,
            "category", Codec.STRING.optional(), CraftingShapelessRecipe::category,
            "result", ExtraCodecs.RESULT.optional(), CraftingShapelessRecipe::result,
            "ingredients", Codec.STRING.list(), CraftingShapelessRecipe::ingredients,
            "type", Codec.STRING, CraftingShapelessRecipe::type,
            ::CraftingShapelessRecipe
        )
    }
}
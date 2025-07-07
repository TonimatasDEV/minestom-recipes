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

class StonecuttingRecipe(val ingredient: String, val result: RecipeResult) : Recipe {
    override fun createRecipeDisplays(): List<RecipeDisplay?> {
        return listOf(
            RecipeDisplay.Stonecutter(
                RecipesUtils.getIngredients(listOf(ingredient)).first(),
                SlotDisplay.ItemStack(result.getResult()),
                SlotDisplay.Item(Material.STONECUTTER)
            )
        )
    }

    override fun craftingRequirements(): List<Ingredient?>? {
        val displaySlots = RecipesUtils.getIngredients(listOf(ingredient))
        return displaySlots.map { IngredientUtils.fromSlotDisplay(it) }.toList()
    }

    override fun recipeBookCategory(): RecipeBookCategory? {
        return RecipeBookCategory.STONECUTTER
    }
    
    companion object {
        val CODEC: StructCodec<StonecuttingRecipe> = StructCodec.struct(
            "ingredient", Codec.STRING.optional(), StonecuttingRecipe::ingredient,
            "result", ExtraCodecs.RESULT.optional(), StonecuttingRecipe::result,
            ::StonecuttingRecipe
        )
    }
}
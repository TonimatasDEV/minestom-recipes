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

class SmeltingRecipe(val result: RecipeResult, val experience: Float, val category: RecipeBookCategory, val cookingTime: Int, val ingredient: String) : Recipe {
    override fun createRecipeDisplays(): List<RecipeDisplay?> {
        return listOf(
            RecipeDisplay.Furnace(
                RecipesUtils.getIngredients(listOf(ingredient)).first(),
                SlotDisplay.AnyFuel.INSTANCE,
                SlotDisplay.ItemStack(result.getResult()),
                SlotDisplay.Item(Material.FURNACE),
                cookingTime,
                experience
            )
        )
    }

    override fun craftingRequirements(): List<Ingredient?>? {
        val displaySlots = RecipesUtils.getIngredients(listOf(ingredient))
        return displaySlots.map { IngredientUtils.fromSlotDisplay(it) }.toList()
    }

    override fun recipeBookCategory(): RecipeBookCategory? {
        return category
    }
    
    companion object {
        val CODEC: StructCodec<SmeltingRecipe> = StructCodec.struct(
            "result", ExtraCodecs.RESULT.optional(), SmeltingRecipe::result,
            "experience", Codec.FLOAT.optional(), SmeltingRecipe::experience,
            "category", RecipeBookCategory.CODEC.optional(), SmeltingRecipe::category,
            "cookingtime", Codec.INT.optional(), SmeltingRecipe::cookingTime,
            "ingredient", Codec.STRING.optional(), SmeltingRecipe::ingredient,
            ::SmeltingRecipe
        )
    }
}
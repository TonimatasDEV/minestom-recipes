package dev.tonimatas.minestomrecipes.types

import dev.tonimatas.minestomrecipes.api.RecipeResult
import dev.tonimatas.minestomrecipes.util.IngredientUtils
import dev.tonimatas.minestomrecipes.util.RecipesUtils
import net.minestom.server.item.Material
import net.minestom.server.recipe.Ingredient
import net.minestom.server.recipe.Recipe
import net.minestom.server.recipe.RecipeBookCategory
import net.minestom.server.recipe.display.RecipeDisplay
import net.minestom.server.recipe.display.SlotDisplay

open class FurnaceRecipe(
    val result: RecipeResult,
    val experience: Float,
    val category: RecipeBookCategory,
    val cookingTime: Int,
    val group: String?,
    val ingredient: String,
    val station: Material
) : Recipe {
    override fun createRecipeDisplays(): List<RecipeDisplay?> {
        return listOf(
            RecipeDisplay.Furnace(
                RecipesUtils.getIngredients(listOf(ingredient)).first(),
                SlotDisplay.AnyFuel.INSTANCE,
                SlotDisplay.ItemStack(result.getResult()),
                SlotDisplay.Item(station),
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

    override fun recipeBookGroup(): String? {
        return group
    }
}
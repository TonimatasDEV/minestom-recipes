package dev.tonimatas.minestomrecipes

import dev.tonimatas.minestomrecipes.api.AllRecipeTypes
import dev.tonimatas.minestomrecipes.util.RecipesUtils
import net.minestom.server.MinecraftServer

object VanillaRecipes {
    private val logger = MinecraftServer.LOGGER

    fun init() {
        val recipes = RecipesUtils.get()

        var count = 0
        for (rawRecipe in recipes) {
            val recipeJson = rawRecipe.asJsonObject
            val recipe = AllRecipeTypes.from(recipeJson)
            
            if (recipe != null) {
                MinecraftServer.getRecipeManager().addRecipe(recipe)
                count++
            }
        }

        logger.info("Remaining {} of total {}", recipes.size() - count, recipes.size()) // TODO: Delete when finish all recipe types
        logger.info("Loaded {} recipes.", count)
    }
}
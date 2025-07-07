package dev.tonimatas.minestomrecipes.types

import dev.tonimatas.minestomrecipes.api.RecipeResult
import dev.tonimatas.minestomrecipes.util.ExtraCodecs
import net.minestom.server.codec.Codec
import net.minestom.server.codec.StructCodec
import net.minestom.server.item.Material
import net.minestom.server.recipe.RecipeBookCategory

class SmeltingRecipe(
    result: RecipeResult,
    experience: Float,
    category: RecipeBookCategory,
    cookingTime: Int,
    group: String?,
    ingredient: String
) : FurnaceRecipe(result, experience, category, cookingTime, group, ingredient, Material.FURNACE) {

    companion object {
        val CODEC: StructCodec<SmeltingRecipe> = StructCodec.struct(
            "result", ExtraCodecs.RESULT.optional(), SmeltingRecipe::result,
            "experience", Codec.FLOAT.optional(), SmeltingRecipe::experience,
            "category", RecipeBookCategory.CODEC.optional(), SmeltingRecipe::category,
            "cookingtime", Codec.INT.optional(), SmeltingRecipe::cookingTime,
            "group", Codec.STRING.optional(), SmeltingRecipe::group,
            "ingredient", Codec.STRING.optional(), SmeltingRecipe::ingredient,
            ::SmeltingRecipe
        )
    }
}
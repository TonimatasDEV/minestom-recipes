package dev.tonimatas.minestomrecipes.types

import dev.tonimatas.minestomrecipes.api.RecipeResult
import dev.tonimatas.minestomrecipes.util.ExtraCodecs
import net.minestom.server.codec.Codec
import net.minestom.server.codec.StructCodec
import net.minestom.server.item.Material
import net.minestom.server.recipe.RecipeBookCategory

class SmokingRecipe(
    result: RecipeResult,
    experience: Float,
    category: RecipeBookCategory,
    cookingTime: Int,
    group: String?,
    ingredient: String
) :
    FurnaceRecipe(result, experience, category, cookingTime, group, ingredient, Material.SMOKER) {

    companion object {
        val CODEC: StructCodec<SmokingRecipe> = StructCodec.struct(
            "result", ExtraCodecs.RESULT.optional(), SmokingRecipe::result,
            "experience", Codec.FLOAT.optional(), SmokingRecipe::experience,
            "category", RecipeBookCategory.CODEC.optional(), SmokingRecipe::category,
            "cookingtime", Codec.INT.optional(), SmokingRecipe::cookingTime,
            "group", Codec.STRING.optional(), SmokingRecipe::group,
            "ingredient", Codec.STRING.optional(), SmokingRecipe::ingredient,
            ::SmokingRecipe
        )
    }
}
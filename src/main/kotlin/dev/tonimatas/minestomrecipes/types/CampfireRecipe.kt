package dev.tonimatas.minestomrecipes.types

import dev.tonimatas.minestomrecipes.api.RecipeResult
import dev.tonimatas.minestomrecipes.util.ExtraCodecs
import net.minestom.server.codec.Codec
import net.minestom.server.codec.StructCodec
import net.minestom.server.item.Material
import net.minestom.server.recipe.RecipeBookCategory

class CampfireRecipe(
    result: RecipeResult,
    experience: Float,
    cookingTime: Int,
    group: String?,
    ingredient: String
) : FurnaceRecipe(result, experience, RecipeBookCategory.CAMPFIRE, cookingTime, group, ingredient, Material.CAMPFIRE) {

    companion object {
        val CODEC: StructCodec<CampfireRecipe> = StructCodec.struct(
            "result", ExtraCodecs.RESULT.optional(), CampfireRecipe::result,
            "experience", Codec.FLOAT.optional(), CampfireRecipe::experience,
            "cookingtime", Codec.INT.optional(), CampfireRecipe::cookingTime,
            "group", Codec.STRING.optional(), CampfireRecipe::group,
            "ingredient", Codec.STRING.optional(), CampfireRecipe::ingredient,
            ::CampfireRecipe
        )
    }
}
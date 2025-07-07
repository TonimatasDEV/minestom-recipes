package dev.tonimatas.minestomrecipes.types

import dev.tonimatas.minestomrecipes.api.RecipeResult
import dev.tonimatas.minestomrecipes.util.ExtraCodecs
import net.minestom.server.codec.Codec
import net.minestom.server.codec.StructCodec
import net.minestom.server.item.Material
import net.minestom.server.recipe.RecipeBookCategory

class BlastingRecipe(
    result: RecipeResult,
    experience: Float,
    category: RecipeBookCategory,
    cookingTime: Int,
    group: String?,
    ingredient: String
) : FurnaceRecipe(result, experience, category, cookingTime, group, ingredient, Material.BLAST_FURNACE) {

    companion object {
        val CODEC: StructCodec<BlastingRecipe> = StructCodec.struct(
            "result", ExtraCodecs.RESULT.optional(), BlastingRecipe::result,
            "experience", Codec.FLOAT.optional(), BlastingRecipe::experience,
            "category", RecipeBookCategory.CODEC.optional(), BlastingRecipe::category,
            "cookingtime", Codec.INT.optional(), BlastingRecipe::cookingTime,
            "group", Codec.STRING.optional(), BlastingRecipe::group,
            "ingredient", Codec.STRING.optional(), BlastingRecipe::ingredient,
            ::BlastingRecipe
        )
    }
}
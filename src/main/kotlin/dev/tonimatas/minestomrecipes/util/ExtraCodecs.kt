package dev.tonimatas.minestomrecipes.util

import dev.tonimatas.minestomrecipes.api.RecipeResult
import net.minestom.server.codec.Codec
import net.minestom.server.codec.StructCodec

object ExtraCodecs {
    val RESULT: StructCodec<RecipeResult> = StructCodec.struct(
        "id", Codec.STRING.optional(), RecipeResult::id,
        "count", Codec.INT.optional(), RecipeResult::count,
        ::RecipeResult
    )
}
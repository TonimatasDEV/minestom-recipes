package dev.tonimatas.minestomrecipes.util

import dev.tonimatas.minestomrecipes.api.RecipeResult
import net.minestom.server.codec.Codec
import net.minestom.server.codec.Result
import net.minestom.server.codec.StructCodec
import net.minestom.server.codec.Transcoder


object ExtraCodecs {
    val RESULT: StructCodec<RecipeResult> = StructCodec.struct(
        "id", Codec.STRING.optional(), RecipeResult::id,
        "count", Codec.INT.optional(), RecipeResult::count,
        ::RecipeResult
    )
    
    val STRING_MAP = mapCodec(Codec.STRING.listOrSingle())

    fun <V> mapCodec(
        valueCodec: Codec<V>
    ): Codec<Map<String, V>> = object : Codec<Map<String, V>> {

        override fun <D> decode(coder: Transcoder<D>, value: D): Result<Map<String, V>> {
            val mapLikeResult = coder.getMap(value!!)
            if (mapLikeResult !is Result.Ok) return mapLikeResult.cast()
            val result = mutableMapOf<String, V>()
            for (key in mapLikeResult.value.keys()) {
                val entryValResult = mapLikeResult.value.getValue(key)
                if (entryValResult !is Result.Ok) continue
                val decoded = valueCodec.decode(coder, entryValResult.value!!)
                if (decoded is Result.Ok) {
                    result[key] = decoded.value
                }
            }
            return Result.Ok(result)
        }

        override fun <D> encode(coder: Transcoder<D>, value: Map<String, V>?): Result<D> {
            if (value == null) return Result.Ok(coder.emptyMap())
            val builder = coder.createMap()
            for ((key, v) in value) {
                val encoded = valueCodec.encode(coder, v)
                if (encoded is Result.Ok) {
                    builder.put(key, encoded.value)
                }
            }
            return Result.Ok(builder.build())
        }
    }
}
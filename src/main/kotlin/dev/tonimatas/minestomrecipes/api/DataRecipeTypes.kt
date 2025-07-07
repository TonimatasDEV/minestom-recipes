package dev.tonimatas.minestomrecipes.api

import com.google.gson.JsonObject
import dev.tonimatas.minestomrecipes.types.*
import net.minestom.server.codec.Transcoder
import net.minestom.server.recipe.Recipe
import java.util.*

enum class DataRecipeTypes {
    CRAFTING_SHAPED,
    CRAFTING_SHAPELESS,
    STONECUTTING,
    CRAFTING_SPECIAL_ARMORDYE,
    SMELTING,
    CAMPFIRE_COOKING,
    SMOKING,
    CRAFTING_SPECIAL_BANNERDUPLICATE,
    CRAFTING_TRANSMUTE,
    SMITHING_TRIM,
    CRAFTING_SPECIAL_BOOKCLONING,
    BLASTING,
    CRAFTING_DECORATED_POT,
    CRAFTING_SPECIAL_FIREWORK_ROCKET,
    CRAFTING_SPECIAL_FIREWORK_STAR,
    CRAFTING_SPECIAL_FIREWORK_STAR_FADE,
    CRAFTING_SPECIAL_MAPCLONING,
    CRAFTING_SPECIAL_MAPEXTENDING,
    SMITHING_TRANSFORM,
    CRAFTING_SPECIAL_REPAIRITEM,
    CRAFTING_SPECIAL_SHIELDDECORATION,
    CRAFTING_SPECIAL_TIPPEDARROW,
    UNKNOWN;
    
    fun get(json: JsonObject): Recipe? {
        return when (this) {
            CRAFTING_SHAPED -> null
            CRAFTING_SHAPELESS -> CraftingShapelessRecipe.CODEC
            STONECUTTING -> StonecuttingRecipe.CODEC
            CRAFTING_SPECIAL_ARMORDYE -> null
            SMELTING -> SmeltingRecipe.CODEC
            CAMPFIRE_COOKING -> CampfireRecipe.CODEC
            SMOKING -> SmokingRecipe.CODEC
            CRAFTING_SPECIAL_BANNERDUPLICATE -> null
            CRAFTING_TRANSMUTE -> null
            SMITHING_TRIM -> null
            CRAFTING_SPECIAL_BOOKCLONING -> null
            BLASTING -> BlastingRecipe.CODEC
            CRAFTING_DECORATED_POT -> null
            CRAFTING_SPECIAL_FIREWORK_ROCKET -> null
            CRAFTING_SPECIAL_FIREWORK_STAR -> null
            CRAFTING_SPECIAL_FIREWORK_STAR_FADE -> null
            CRAFTING_SPECIAL_MAPCLONING -> null
            CRAFTING_SPECIAL_MAPEXTENDING -> null
            SMITHING_TRANSFORM -> null
            CRAFTING_SPECIAL_REPAIRITEM -> null
            CRAFTING_SPECIAL_SHIELDDECORATION -> null
            CRAFTING_SPECIAL_TIPPEDARROW -> null
            UNKNOWN -> null
        }?.decode(Transcoder.JSON, json)?.orElse(null)
    }
    
    companion object {
        fun from(recipe: JsonObject): Recipe? {
            val rawType = recipe["type"].asString
            val typeString = rawType.replace("minecraft:", "")
            val type = valueOf(typeString.uppercase(Locale.ENGLISH))
            
            if (type == CAMPFIRE_COOKING) {
                println("Recipe: ${recipe.get("ingredient").asString}")
            }
            
            return type.get(recipe)
        }
    }
}
import org.jetbrains.kotlin.com.google.gson.GsonBuilder
import org.jetbrains.kotlin.com.google.gson.JsonArray

plugins {
    kotlin("jvm") version "2.1.21"
}

group = "dev.tonimatas.minestomrecipes"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.minestom)
}

kotlin {
    jvmToolchain(21)
}

tasks.register<Task>("parseRecipes") {
    val gson = GsonBuilder().setPrettyPrinting().create()!!
    val file = File("src/main/resources/recipes.json")

    if (!file.exists()) throw RuntimeException("Recipes file not found.")

    val recipesJson = file.reader().use { reader ->
        gson.fromJson(reader, JsonArray::class.java)
    }

    recipesJson.asJsonArray.forEach { recipe ->
        val type = recipe.asJsonObject.get("type").asString

        val categoryJson = recipe.asJsonObject.get("category")
        
        if (categoryJson != null) {
            val category = categoryJson.asString
            when (type) {
                "minecraft:smelting" -> {
                    if (category != null && !category.startsWith("furnace_")) {
                        recipe.asJsonObject.addProperty("category", "furnace_$category")
                    }
                }

                "minecraft:crafting_shapeless" -> {
                    if (category != null && !category.startsWith("crafting_")) {
                        if (category == "building") {
                            recipe.asJsonObject.addProperty("category", "crafting_${category}_blocks")
                            return@forEach
                        }

                        recipe.asJsonObject.addProperty("category", "crafting_$category")
                    }
                }
                
                "minecraft:blasting" -> {
                    if (category != null && !category.startsWith("blast_furnace_")) {
                        recipe.asJsonObject.addProperty("category", "blast_furnace_$category")
                    }
                }
                
                "minecraft:smoking" -> {
                    if (category != null && !category.startsWith("smoker_")) {
                        recipe.asJsonObject.addProperty("category", "smoker_$category")
                    }
                }
            }
        }
        
    }

    val newJson = gson.toJson(recipesJson)!!
    file.writeText(newJson)
}
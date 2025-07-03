# Minestom Recipes

It is a library that implements all Minecraft recipes.

# Installation
You should add this library to your dependency manager. [Here](https://maven.tonimatas.dev/#) is the maven repository and how to add it.

## Getting started

Here are an example of how to use this library. (It is the same in both languages)

### Kotlin:
```kotlin
fun start() {
    val server = MinecraftServer.init()

    VanillaRecipes.init()

    server.start("0.0.0.0", 25565)
}
```

### Java:
```java
public void start() {
    MinecraftServer server = MinecraftServer.init();

    VanillaRecipes.init();

    server.start("0.0.0.0", 25565);
}
```

# Credits

Thanks to [Extractor](https://github.com/Pumpkin-MC/Extractor), it is the tool used to create the recipes.json
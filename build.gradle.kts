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

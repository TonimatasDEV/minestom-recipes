plugins {
    application
    kotlin("jvm")
}

group = "dev.tonimatas.minestomrecipes"
version = parent!!.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(rootProject)
    implementation(libs.bundles.server)
}

application {
    mainClass.set("dev.tonimatas.test.TestServer")
}

kotlin {
    jvmToolchain(21)
}
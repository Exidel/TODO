import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0-alpha4-build396"
    kotlin("plugin.serialization") version "1.5.31"
}

group = "Exidel"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "15"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "TODO"
            packageVersion = "1.0.0"
        }
    }
}
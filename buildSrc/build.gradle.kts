import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//////////////////////////////////////////////////////////////////////////
// Kotlin dsl support
///////////////////////////////////////////////////////////////////////////
plugins {
    `kotlin-dsl`
    id("org.jetbrains.kotlin.jvm") version "1.3.50"
}

repositories {
    jcenter()
    google()
}

//////////////////////////////////////////////////////////////////////////
// Kotlin support
//////////////////////////////////////////////////////////////////////////
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

///////////////////////////////////////////////////////////////////////////
// Basic - Groovy support
///////////////////////////////////////////////////////////////////////////
apply(plugin = "groovy")

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:3.4.2")
    implementation("org.javassist:javassist:3.25.0-GA")
}

// Build Kotlin before Groovy
tasks.withType(GroovyCompile::class) {
    dependsOn(compileKotlin)
    classpath += files(compileKotlin.destinationDir)
}

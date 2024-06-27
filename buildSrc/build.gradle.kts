import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}
// Required since Gradle 4.10+.
repositories {
    google()
    jcenter()
    maven(url = "https://jitpack.io")
    maven(url = "https://maven.google.com")
}


object PluginVersions {
    /*  const val gradle = "4.2.2"
      const val kotlin = "1.5.31"
      const val googleServices = "4.3.3"*/
    const val gradle = "8.1.1"
    const val kotlin = "1.9.0"
  //  const val googleServices = "4.3.3"
    const val javapoet = "1.13.0"
}

object Plugins {
    const val gradleDependency = "com.android.tools.build:gradle:${PluginVersions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlin}"
   // const val googleServices = "com.google.gms:google-services:${PluginVersions.googleServices}"
    const val javapoet = "com.squareup:javapoet:${PluginVersions.javapoet}"

}

dependencies {
    implementation(Plugins.gradleDependency)
   // implementation(Plugins.googleServices)
    implementation(Plugins.kotlinGradle)
    implementation(Plugins.javapoet)

}

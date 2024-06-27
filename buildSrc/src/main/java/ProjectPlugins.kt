object ProjectPlugins {
    const val pathGradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val pathGradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServicesPlugin}"
    const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktLint}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger_hilt}"
    const val oneSignal = "gradle.plugin.com.onesignal:onesignal-gradle-plugin:${Versions.oneSignalVersion}"
}

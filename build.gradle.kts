buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        gradlePluginPortal()
        maven(url = "https://jitpack.io")
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(ProjectPlugins.pathGradle)
        classpath(ProjectPlugins.pathGradleKotlin)
      //  classpath(ProjectPlugins.googleServices)
        classpath(ProjectPlugins.navigation)
        classpath(ProjectPlugins.ktLint)
        classpath(ProjectPlugins.hilt)
        classpath(ProjectPlugins.oneSignal)
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.9.7")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://android-sdk.payfort.com")
        maven(url = "https://sdk.smartlook.com/android/release")
    }
    apply(plugin = Plugins.ktLint)
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        disabledRules.set(setOf("final-newline"))
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }
        kotlinScriptAdditionalPaths {
            include(fileTree("scripts/"))
        }
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}

tasks.create<Delete>("cleanRP") {
    group = "rp"
    delete = setOf(
        "rp-out",
        "rp-zip"
    )
}

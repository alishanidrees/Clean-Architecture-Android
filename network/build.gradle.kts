import extensions.implementation
import extensions.kapt

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {
    namespace = "com.perfectlypressed.network"
    compileSdk = (AndroidConfig.compileSdk)

    defaultConfig.apply {
        minSdk = (AndroidConfig.minSdk)
        targetSdk = (AndroidConfig.targetSdk)
    }
    buildTypes {
        getByName(BuildType.RELEASE) {
            isJniDebuggable = BuildTypeProd.isDebuggable
            buildConfigField(
                "String",
                BuildConfigVariables.APP_BASE_URL,
                BuildConfigVariables.RELEASE_APP_BASE_URL
            )
        }

        maybeCreate(BuildType.SIT).apply {
            isJniDebuggable = BuildTypeSit.isDebuggable
            buildConfigField(
                "String",
                BuildConfigVariables.APP_BASE_URL,
                BuildConfigVariables.SIT_APP_BASE_URL
            )
        }

        maybeCreate(BuildType.UAT).apply {
            isJniDebuggable = BuildTypeUat.isDebuggable
            buildConfigField(
                "String",
                BuildConfigVariables.APP_BASE_URL,
                BuildConfigVariables.UAT_APP_BASE_URL
            )
        }
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    kapt {
        generateStubs = true
    }
}

dependencies {
    api("com.bugsee:bugsee-android:3.10.0")
    implementation(Dependencies.dagger_hilt)
    implementation(Dependencies.retrofitGson)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.okhttp3)
    implementation(Dependencies.okhttp3Interceptor)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.timber)
    kapt(Dependencies.hiltCompiler)
}
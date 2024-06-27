import extensions.implementation
import extensions.testImplementation
import java.io.FileInputStream
import java.util.Properties

buildscript {
    repositories {
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}

repositories {
    jcenter()
    maven(url = "https://maven.google.com")
}

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
   // id(Plugins.googleServices)
    id(Plugins.naivgationSageArgs)
    id(Plugins.hilt)
    id(Plugins.parcelize)
    id(Plugins.oneSignal)
    id(Plugins.crashlytics)
    id("org.jetbrains.kotlin.android")
}

/*fun getProps(path: String = "signing-tawkeel.properties"): Properties {
    val props = Properties()
    props.load(FileInputStream(rootProject.file(path)))
    return props
}*/

val Properties.getStorePath: String
    get() = getProperty("storeFile")

val Properties.getKeyStorePassword: String
    get() = getProperty("storePassword")

val Properties.getKeyAlias: String
    get() = getProperty("keyAlias")

val Properties.getKeyPassword: String
    get() = getProperty("keyPassword")

android {
    namespace = AndroidConfig.applicationId
    compileSdk = AndroidConfig.compileSdk
    defaultConfig.apply {
        applicationId = AndroidConfig.applicationId
        minSdk  = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
        setProperty("archivesBaseName", "camera")
        multiDexEnabled = true
        testInstrumentationRunner = AndroidConfig.androidTestInstrumentationRunner
    }

    buildFeatures {
        buildConfig = true
        dataBinding = true
        viewBinding = true
    }

    /*signingConfigs {
        maybeCreate(BuildType.RELEASE).apply {
            getProps().apply {
                storeFile = file(getStorePath)
                storePassword = getKeyStorePassword
                keyAlias = getKeyAlias
                keyPassword = getKeyPassword
            }
        }
    }*/

    buildTypes {
       /* getByName(BuildType.RELEASE) {
            applicationIdSuffix = BuildTypeProd.applicationSuffix
            isMinifyEnabled = BuildTypeProd.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeProd.isTestCoverageEnabled
            isDebuggable = BuildTypeProd.isDebuggable
            signingConfig = signingConfigs.getByName(BuildType.RELEASE)
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField(
                "String",
                BuildConfigVariables.ONE_SIGNAL_ID,
                BuildConfigVariables.RELEASE_ONE_SIGNAL_ID
            )
            buildConfigField(
                "String",
                BuildConfigVariables.BLOB_URL,
                BuildConfigVariables.RELEASE_BLOB_URL
            )
            buildConfigField(
                "String",
                BuildConfigVariables.APPS_FLYER_KEY,
                BuildConfigVariables.RELEASE_APPS_FLYER_KEY
            )
        }*/

        maybeCreate(BuildType.SIT).apply {
            initWith(getByName(BuildType.DEBUG))
            applicationIdSuffix = BuildTypeSit.applicationSuffix
            versionNameSuffix = BuildTypeSit.versionNameSuffix
            isMinifyEnabled = BuildTypeSit.isMinifyEnabled
            isDebuggable = BuildTypeSit.isDebuggable
            enableUnitTestCoverage = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField(
                "String",
                BuildConfigVariables.ONE_SIGNAL_ID,
                BuildConfigVariables.SIT_ONE_SIGNAL_ID
            )
            buildConfigField(
                "String",
                BuildConfigVariables.BLOB_URL,
                BuildConfigVariables.SIT_BLOB_URL
            )
            buildConfigField(
                "String",
                BuildConfigVariables.APPS_FLYER_KEY,
                BuildConfigVariables.DEBUG_APPS_FLYER_KEY
            )
        }

        maybeCreate(BuildType.UAT).apply {
            initWith(getByName(BuildType.DEBUG))
            applicationIdSuffix = BuildTypeUat.applicationSuffix
            versionNameSuffix = BuildTypeUat.versionNameSuffix
            isMinifyEnabled = BuildTypeUat.isMinifyEnabled
            isDebuggable = BuildTypeUat.isDebuggable
            isTestCoverageEnabled = BuildTypeUat.isTestCoverageEnabled
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField(
                "String",
                BuildConfigVariables.ONE_SIGNAL_ID,
                BuildConfigVariables.UAT_ONE_SIGNAL_ID
            )
            buildConfigField(
                "String",
                BuildConfigVariables.BLOB_URL,
                BuildConfigVariables.UAT_BLOB_URL
            )
            buildConfigField(
                "String",
                BuildConfigVariables.APPS_FLYER_KEY,
                BuildConfigVariables.DEBUG_APPS_FLYER_KEY
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlin {
        jvmToolchain(17)
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    dexOptions {
        preDexLibraries = true
        jumboMode = false
    }

    kapt {
        generateStubs = true
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }


    dependencies {
        implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
        implementation(project(":network"))

        implementation(Dependencies.workManagerRuntime)
        implementation(Dependencies.mapUtils)
        implementation(Dependencies.freshChat)
        implementation(Dependencies.mixPanel)
        implementation(Dependencies.firestoreKTX)
        implementation(Dependencies.crashlytics)
        implementation(Dependencies.appsFlyer)
        implementation(Dependencies.kotlinStdlibJdk)
//        implementation(Dependencies.kotlinCoreKtx)
        implementation(Dependencies.androidAppcompat)
        implementation(Dependencies.androidConstraintLayout)
        implementation(Dependencies.androidRecyclerView)
        implementation(Dependencies.materialDesignNew)
        implementation(Dependencies.dagger_hilt)
        implementation(Dependencies.retrofitGson)
        implementation(Dependencies.retrofit)
        implementation(Dependencies.okhttp3)
        implementation(Dependencies.okhttp3Interceptor)
        implementation(Dependencies.coil)
        implementation(Dependencies.activityKtx)
        implementation(Dependencies.fragmentKtx)
        implementation(Dependencies.coreKtx)
        implementation(Dependencies.kotlinPlayServices)
        implementation(Dependencies.googleAuth)
        implementation(Dependencies.googlePlacePicker)
        implementation(Dependencies.googleAnalytics)
        implementation(Dependencies.googleFirebase)
        implementation(Dependencies.firebaseAuth)
        implementation(Dependencies.remoteConfig)
        implementation(platform(Dependencies.firebaseBOM))
        implementation(Dependencies.paging)
        implementation(Dependencies.navigationFragment)
        implementation(Dependencies.navigationUi)
        implementation(Dependencies.hiltNavigationFragment)

        //FFmpegLib
        implementation(Dependencies.FFmpegLib)

        implementation(Dependencies.lifeCycleRuntimeExtensions)
        implementation(Dependencies.lifeCycleViewModel)
        implementation(Dependencies.lifeCycleLiveData)
        implementation(Dependencies.lifeCycleLiveDataCore)
        implementation(Dependencies.lifeCycleJava8)
        implementation(Dependencies.lifeCycleSavedStateViewModel)
        implementation(Dependencies.splash)


        // third-party
        implementation(Dependencies.countryCodePicker)
        implementation(Dependencies.dimensions)
        implementation(Dependencies.dexter)
        implementation(Dependencies.stepsCounter)
        implementation(Dependencies.oneSignal)
        implementation(Dependencies.facebook)
        implementation(Dependencies.timber)
        implementation(Dependencies.coroutines)
        implementation(Dependencies.coroutinesAndroid)
        implementation(Dependencies.lottie)
        implementation(Dependencies.playServicesLocation)
        implementation(Dependencies.imageCropper)
        implementation(Dependencies.gradientColor)
        implementation(Dependencies.rxAnimation)
        implementation(Dependencies.animations)
        implementation(Dependencies.roundedImageView)
        implementation(Dependencies.circularImageView)
        implementation(Dependencies.ratingBar)
        implementation(Dependencies.biometric)
        coreLibraryDesugaring(Dependencies.coreLibraryDesugaring)
        kapt(Dependencies.lifeCycleCompiler)
        kapt(Dependencies.hiltCompiler)
        testImplementation(Dependencies.androidXTesting)
        testImplementation(Dependencies.mockitoKotlin)
        testImplementation(Dependencies.coroutinesTest)
        testImplementation(Dependencies.coroutinesCoreTest)
        testImplementation(Dependencies.mockitoCore)
        testImplementation(Dependencies.mockitoInline)
    }
}
//dependencies {
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.9.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
//}

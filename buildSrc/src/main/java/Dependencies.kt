@Suppress("unused", "MayBeConstant")
object Dependencies {
//    val kotlinCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val kotlinStdlibJdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    val dagger_hilt = "com.google.dagger:hilt-android:${Versions.dagger_hilt}"
    val hilt = "androidx.hilt:hilt:${Versions.hilt}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger_hilt}"
    val hiltNavigationFragment = "androidx.hilt:hilt-navigation-fragment:${Versions.hiltNavigationFragment}"

    val androidMultiDex = "com.android.support:multidex:${Versions.multiDex}"
    val dimensions = "com.intuit.sdp:sdp-android:${Versions.dimensions}"
    val dexter = "com.karumi:dexter:${Versions.dexter}"
    val stepsCounter = "com.jjoe64:graphview:${Versions.stepsCounter}"

    val androidAppcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val androidConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    val androidRecyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    val materialDesignNew = "com.google.android.material:material:${Versions.materialDesignNew}"

    val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val okhttp3Interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    val coil = "io.coil-kt:coil:${Versions.coil}"

    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val FFmpegLib = "com.arthenica:ffmpeg-kit-full:${Versions.FFmpeg}"

    val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKTXVersion}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKTXVersion}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKTXVersion}"
    val kotlinPlayServices =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.kotlinPlayServices}"

    val googleAuth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    val googlePlacePicker =
        "com.google.android.gms:play-services-places:${Versions.googlePlacePicker}"

    val googleAnalytics = "com.google.firebase:firebase-analytics-ktx"
    val googleFirebase = "com.google.firebase:firebase-core:${Versions.firebase}"
    val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
    val firebaseConfig = "com.google.firebase:firebase-config:${Versions.firebaseConfigVersion}"
    val firebaseMessaging = "com.google.firebase:firebase-messaging-ktx"
    val remoteConfig = "com.google.firebase:firebase-config-ktx"
    val firebaseDatabase = "com.google.firebase:firebase-database:${Versions.firebaseDatabase}"
    val firebaseUI = "com.firebaseui:firebase-ui-database:${Versions.firebaseUI}"
    val firebaseBOM = "com.google.firebase:firebase-bom:${Versions.firebaseBOM}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val lifeCycleRuntimeExtensions =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
    const val lifeCycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val lifeCycleLiveDataCore =
        "androidx.lifecycle:lifecycle-livedata-core-ktx:${Versions.lifeCycle}"
    const val lifeCycleJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
    const val lifeCycleSavedStateViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifeCycle}"
    const val lifeCycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifeCycle}"
    const val circularImageView = "de.hdodenhof:circleimageview:${Versions.circularImageview}"
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val countryCodePicker = "com.hbb20:ccp:${Versions.countryCodePicker}"
    const val oneSignal = "com.onesignal:OneSignal:${Versions.oneSignal}"
    const val facebook = "com.facebook.android:facebook-login:${Versions.facebook}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val playServicesLocation = "com.google.android.gms:play-services-location:${Versions.playServicesLocation}"
    const val imageCropper = "com.github.CanHub:Android-Image-Cropper:${Versions.imageCropper}"
    const val coreLibraryDesugaring = "com.android.tools:desugar_jdk_libs:${Versions.coreLibraryDesugaring}"
    const val workManagerRuntime = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    const val mapUtils = "com.google.maps.android:android-maps-utils:${Versions.mapUtils}"
    const val freshChat = "com.github.freshworks:freshchat-android:${Versions.freshChat}"
    const val mixPanel = "com.mixpanel.android:mixpanel-android:${Versions.mixPanel}"
    const val firestoreKTX = "com.google.firebase:firebase-firestore-ktx"
    const val crashlytics = "com.google.firebase:firebase-crashlytics"
    const val appsFlyer = "com.appsflyer:af-android-sdk:${Versions.appsFlyer}"
    const val ratingBar = "com.github.ome450901:SimpleRatingBar:${Versions.ratingBar}"

    const val androidXTesting = "androidx.arch.core:core-testing:${Versions.androidXTesting}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val coroutinesCoreTest = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCoreTest}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val gradientColor = "com.github.veeyaarVR:SuperGradientTextView:${Versions.gradientColor}"
    const val rxAnimation = "com.mikhaellopez:rxanimation:${Versions.rxAnimation}"
    const val animations = "com.daimajia.androidanimations:${Versions.animations}"
    const val roundedImageView = "com.makeramen:roundedimageview:${Versions.roundedImgView}"

    const val splash = "androidx.core:core-splashscreen:${Versions.splash}"
    const val biometric = "androidx.biometric:biometric:${Versions.biometric}"


}


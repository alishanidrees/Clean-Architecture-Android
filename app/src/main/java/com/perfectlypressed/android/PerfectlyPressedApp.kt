package com.perfectlypressed.android

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.bugsee.library.Bugsee
import com.bugsee.library.LaunchOptions
import com.bugsee.library.data.IssueSeverity
import com.onesignal.OneSignal
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class PerfectlyPressedApp : Application() {


    override fun onCreate() {
        super.onCreate()
        initTimber()
       // launchBugsee()
    }


   /* private fun launchBugsee() {
        if (BuildConfig.DEBUG) {
            val options = LaunchOptions()
            options.Network.isMonitorNetwork = true
            options.Network.maxNetworkBodySize = 200_000 // 4 times the default body size for translation issues
            options.General.isWifiOnlyUpload = true
            options.General.maxDataSize = 200
            options.General.isShakeToTrigger = true
            options.Video.maxRecordingTime = 300
            options.General.isCrashReport = true
            options.General.isReportPrioritySelector = true
            options.General.defaultBugPriority = IssueSeverity.VeryLow
            Bugsee.launch(this, remoteConfig.getBugseeToken(), options)
        }
    }*/

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

   /* private fun initRemoteConfig() {
        remoteConfig.init()
    }*/



   /* private fun initiateDeepLinking() {
        val appsFlyerLib = AppsFlyerLib.getInstance()
        if (BuildConfig.DEBUG) {
            appsFlyerLib.setDebugLog(true)
        }
        appsFlyerLib.init(BuildConfig.APPS_FLYER_KEY, null, this)
        appsFlyerLib.start(this)
        deepLinkService.setupListener { deepLink ->
            cacheDataManager.setAppsFlyerDeepLink(deepLink.default)
            onDeepLinkTrigger(deepLink.default)
        }
    }*/

  /*  private fun onDeepLinkTrigger(deepLink: String) {
        if (deepLink == Constants.PERFORMER_SIGN_UP) {
            cacheDataManager.setUserAccountType(AccountType.Performer)
            cacheDataManager.setUserAccountTypePreLogin(AccountType.Performer)
        } else {
            cacheDataManager.setUserAccountType(AccountType.Seeker)
        }
    }*/

 /*   companion object{
        lateinit var cache: SimpleCache
    }*/

    /*private fun initOneSignal() {
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(BuildConfig.ONE_SIGNAL_ID)
    }*/

}

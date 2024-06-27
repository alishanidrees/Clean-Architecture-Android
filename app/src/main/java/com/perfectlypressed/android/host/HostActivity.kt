package com.perfectlypressed.android.host

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.onesignal.OneSignal
import com.perfectlypressed.R
import com.perfectlypressed.android.base.domain.RuntimePermissionListener
import com.perfectlypressed.android.base.presentation.ProgressLoader
import com.perfectlypressed.android.extentions.popBackStack
import com.perfectlypressed.android.extentions.restartHost
import com.perfectlypressed.android.extentions.showToast
import com.perfectlypressed.databinding.ActivityHostBinding
import com.perfectlypressed.network.NetworkPreferencesManager
import com.perfectlypressed.network.providers.AppLocaleProvider
import com.tawkeel.base.domain.SingleLiveEvent
import com.tawkeel.navigation.FragmentNavigator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HostActivity : AppCompatActivity() {

    lateinit var binding: ActivityHostBinding
    private val hostViewModel: HostViewModel by viewModels()
    val bottomNavMenuItemId: LiveData<Int>
        get() = _bottomNavMenuItemId
    private val _bottomNavMenuItemId: SingleLiveEvent<Int> by lazy {
        SingleLiveEvent()
    }
   // private val deepLinkViewModel: DeepLinkViewModel by viewModels()

    private val progressLoader: ProgressLoader by lazy {
        ProgressLoader(this)
    }

    private lateinit var appLocaleProvider: AppLocaleProvider
    private lateinit var networkPreferencesManager: NetworkPreferencesManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityHostBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@HostActivity
            viewModel = hostViewModel
           // translationManager = hostViewModel.translationManager
        }
        setContentView(binding.root)
        setupViews()
        observeLiveDataFromViewModel()
      // hostViewModel.uploadRequest(this)
        enableNotificationsPermission()
    }


    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    override fun onStop() {
        super.onStop()
      //  hostViewModel.setDeepLinkToEmpty()
    }

    override fun onResume() {
        super.onResume()
       // checkDeepLink()
      //  clearNotification()
    }

    private fun checkDeepLink() {
        val link = intent
        Timber.i("custom-deeplink ${link.data}")
        if (intent.data != null) {
            intent.data = null
          //  hostViewModel.initiateDeepLinking()
        }
    }
    private fun getNavController(): NavController? {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()
    }

   /* private fun updateFooter() {
        val menu = binding.bottomNavigationView.menu
        updateTranslationsForMenu(menu)
    }*/

    private fun clearNotification() {
        OneSignal.clearOneSignalNotifications()
    }

    private fun updateTranslationsForMenu(menu: Menu) {
        //hostViewModel.setBottomNavigationMenuTranslations(menu.children.iterator())
    }

   /* override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        try {
            val view: View = currentFocus ?: return super.dispatchTouchEvent(ev)
            if ((ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) &&
                view is EditText && view.javaClass.name.startsWith("android.webkit.").inverse
            ) {
                val array = IntArray(2)
                view.getLocationOnScreen(array)
                val x: Float = ev.rawX + view.left - array[0]
                val y: Float = ev.rawY + view.top - array[1]
                if (x < view.left || x > view.right || y < view.top || y > view.bottom) {
                    view.hideKeyboard()
                }
            }
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun initialiseDeepLinking() {
        deepLinkViewModel.run {
            if (DeepLinkBroadcastLiveData.hasActiveObservers().inverse) {
                DeepLinkBroadcastLiveData.observe(this@HostActivity) { notificationOrderData ->
                    deepLinkViewModel.onBroadcastLiveDataResult(notificationOrderData)
                }
            }

            currentDestinationName = {
                getNavController()?.currentDestination.getClassName()
            }
            getNavController()?.addOnDestinationChangedListener { _, destination, _ ->
                updateFooter()
                when (destination.id) {
                    R.id.performer_home,
                    R.id.seeker_home -> {
                        attachDeepLinkObservers()
                    }

                   *//* R.id.chat -> {
                        when (hostViewModel.chatNavigation()) {
                            ChatNavigation.FRESH_DESK -> {
                                navController().popBackStack()
                                hostViewModel.navigateToFreshDeskSupport()
                            }

                            ChatNavigation.WHATSAPP -> {
                                navController().popBackStack()
                                hostViewModel.openWhatsapp()
                            }
                        }
                    }*//*

                    else -> return@addOnDestinationChangedListener
                }
            }
            observeOneSignalNotificationReceiver()
        }
    }

    private fun attachDeepLinkObservers() {
        if (DeepLinkBroadcastLiveData.hasActiveObservers().inverse) {
            DeepLinkBroadcastLiveData.observe(this@HostActivity) { notificationOrderData ->
                deepLinkViewModel.onBroadcastLiveDataResult(notificationOrderData)
            }
        }
    }



    private fun setupViews() {
        hostViewModel.updateCurrentDisplayMode()
        getNavController()?.let { navController ->
            binding.bottomNavigationView.setupWithNavController(navController)
        }
    }*/

   /* private fun getNavController(): NavController? {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()
    }*/

    private fun observeLiveDataFromViewModel() {
        hostViewModel.run {

            bottomNavMenuItemId.observe(this@HostActivity) { menuItem ->
                popBackStack(menuItem, false)
            }
            loadingLiveData.observe(this@HostActivity, ::showOrHideProgress)
            restartActivityLiveData.observe(this@HostActivity) {
                restartHost()
            }
         //   observeNavigationLiveData()
        }
    }
   private fun setupViews() {
       hostViewModel.updateCurrentDisplayMode()
       getNavController()?.let { navController ->
           binding.bottomNavigationView.setupWithNavController(navController)
       }
   }
    private fun showOrHideProgress(showProgress: Boolean) {
        progressLoader.showOrHideProgress(showProgress)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }


   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == Constants.REQUEST_FORT_SDK && data != null) {
                hostViewModel.fortCallBackManager?.onActivityResult(requestCode, resultCode, data)
            }
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
        }
    }

    override fun attachBaseContext(base: Context?) {
        base?.let {
            networkPreferencesManager = DefaultNetworkPreferencesManager(context = base)
            appLocaleProvider = DefaultAppLocaleProvider(base, networkPreferencesManager)
            val locale = Locale(appLocaleProvider.getLocaleCode())
            super.attachBaseContext(ContextWrapper.wrap(base, locale))
        } ?: super.attachBaseContext(base)
    }

    private fun observeNavigationLiveData() {
        deepLinkViewModel.navigationLiveData.observe(this) { navigator ->
            when (navigator) {
                is FragmentNavigator.Push -> {
                    if (!hasOpenedDialogs(this))
                        navigate(navigator.navDirections)
                }

                is FragmentNavigator.BottomNavItem -> navigate(navigator.menuItemId)
                is FragmentNavigator.SupportService -> hostViewModel.navigateToFreshDeskSupport()
                else -> return@observe
            }
        }
        deepLinkViewModel.uiMessageLiveData.observe(this, ::handleUiMessage)
        deepLinkViewModel.loadingLiveData.observe(this, ::showOrHideProgress)
    }


    private fun handleUiMessage(uiMessage: UIMessage) {
        when (uiMessage) {
            is UIMessage.ToastMessage -> showToast(uiMessage.message)
            is UIMessage.DialogMessage -> hostViewModel.showDialog(
                this,
                uiMessage.dialogMessageType,
                uiMessage.clickCallback
            )

            else -> {}
        }
    }

    private fun showOrHideProgress(showProgress: Boolean) {
        progressLoader.showOrHideProgress(showProgress)
    }*/

    override fun onDestroy() {
        super.onDestroy()
      //  hostViewModel.stopHandler()
    }

    private fun enableNotificationsPermission() {
        if (Build.VERSION.SDK_INT >= 33) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }


   /* override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        setIntent(intent)
    }*/
}

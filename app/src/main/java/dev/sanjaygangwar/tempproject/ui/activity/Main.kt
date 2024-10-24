package dev.sanjaygangwar.tempproject.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.tasks.Task
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import dagger.hilt.android.AndroidEntryPoint
import dev.sanjaygangwar.tempproject.R
import dev.sanjaygangwar.tempproject.databinding.MainBinding
import dev.sanjaygangwar.tempproject.models.dataclass.GenericDialogModelClass
import dev.sanjaygangwar.tempproject.models.protoclass.appsetting.Language
import dev.sanjaygangwar.tempproject.ui.dialogs.GenericDialog
import dev.sanjaygangwar.tempproject.ui.dialogs.NoInternetConnection
import dev.sanjaygangwar.tempproject.utils.baseclasses.BaseActivity
import dev.sanjaygangwar.tempproject.utils.datastore.AppSetting
import dev.sanjaygangwar.tempproject.utils.extenstionfuntions.Extensions.hide
import dev.sanjaygangwar.tempproject.utils.extenstionfuntions.Extensions.show
import dev.sanjaygangwar.tempproject.utils.extenstionfuntions.VariableConst.Notification
import dev.sanjaygangwar.tempproject.utils.mUtils.mLog
import dev.sanjaygangwar.tempproject.utils.network.NetworkObserver
import dev.sanjaygangwar.tempproject.workers.DummyWorker.DummyWorkers.startDummyWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class Main : BaseActivity<MainBinding>(MainBinding::inflate), GenericDialog.GenericDialogListener {

    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkObserver: NetworkObserver

    private var appUpdateManager: AppUpdateManager? = null
    private var reviewManager: ReviewManager? = null
    override fun getDataFromTheServer() {}

    override fun initAllComponents() {
        initNetworkObserver()
        checkForUpdates()
        initAdsSdk()
        initAllPermission()
        setupLoader()

//        Shared Preferences and Worker -------------------------------------
//        CoroutineScope(Dispatchers.IO).launch {
//            setLanguage(Language.GERMAN)
//        }
//        //read data from datastore
//        CoroutineScope(Dispatchers.IO).launch {
//            applicationContext.AppSetting.data.collectLatest {
//               mLog("SANJAY DATA STORE IS ::: $it")
//           }
//        }
//        startDummyWorker(context = this)
    //        Shared Preferences and Worker -------------------------------------
    }

    private fun setupLoader() {
        bind?.lottie?.setAnimation(getLoaders())
    }

    private fun getLoaders(): Int {
        return when (Random.nextInt(3)) {
            0 -> R.raw.loader0
            1 -> R.raw.loader1
            2 -> R.raw.loader2
            3 -> R.raw.loader3
            else -> R.raw.loader0
        }
    }

    fun showLoader() {
        bind?.loader?.show()
    }

    fun hideLoader() {
        bind?.loader?.hide()
    }

    private fun initAllPermission() {
        checkForNotificationPermission()
    }

    private fun checkForNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val genericDialog = GenericDialog(
                    Notification,
                    GenericDialogModelClass(
                        getString(R.string.notification_descripton_two) + "${getString(R.string.app_name)} " + getString(
                            R.string.notification_descrippton_two
                        ),
                        "Allow",
                        "Dismiss",
                        R.raw.notication_bell
                    ),
                    this
                )
                genericDialog.showNow(supportFragmentManager, genericDialog.tag)
            }
        }
    }

    private fun initAdsSdk() {
        AudienceNetworkAds.initialize(this)
        MobileAds.initialize(this)
    }

    override fun initAllObserver() {}

    override fun initOnClickListener() {}
    override fun onViewClicker(p0: View?) {}

    private fun initNetworkObserver() {
        val noInternetDialog = NoInternetConnection()
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkObserver = NetworkObserver(connectivityManager)
        networkObserver.isConnected.observe(this) { isConnected ->
            if (isConnected) {
                noInternetDialog.dismiss()
            } else {
                noInternetDialog.showNow(supportFragmentManager, noInternetDialog.tag)
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkObserver)
    }

    private fun checkForUpdates() {
        appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager?.appUpdateInfo
        appUpdateInfoTask?.addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(
                    AppUpdateType.IMMEDIATE
                )
            ) {
                try {
                    appUpdateManager?.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        100
                    )
                } catch (e: IntentSender.SendIntentException) {
                    e.printStackTrace()
                    mLog(e.message.toString())
                }

            }
        }
    }


    private fun showRateApp() {
        reviewManager?.let {
            val request: Task<ReviewInfo> = it.requestReviewFlow()
            request.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val reviewInfo: ReviewInfo = task.result
                    val flow: Task<Void> = it.launchReviewFlow(this, reviewInfo)
                    flow.addOnCompleteListener { }
                }
            }
        }
    }


    @SuppressLint("InlinedApi")
    override fun onPositiveClick(flag: String) {
        when (flag) {
            Notification -> {
                val showRationale =
                    shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS);
                if (!showRationale) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        1
                    )
                } else {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.parse("package:${this.packageName}")
                    startActivity(intent)
                }
            }
        }
    }

    override fun onNegativeClick(flag: String) {}

    private suspend fun setLanguage(language: Language) {
        applicationContext.AppSetting.updateData {
            it.copy(language = language)
        }
    }
}
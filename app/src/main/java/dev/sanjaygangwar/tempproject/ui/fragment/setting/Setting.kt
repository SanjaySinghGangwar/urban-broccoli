package dev.sanjaygangwar.tempproject.ui.fragment.setting

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.sanjaygangwar.tempproject.R
import dev.sanjaygangwar.tempproject.databinding.SettingBinding
import dev.sanjaygangwar.tempproject.utils.baseclasses.BaseFragment
import dev.sanjaygangwar.tempproject.utils.extenstionfuntions.Extensions.hide
import dev.sanjaygangwar.tempproject.utils.extenstionfuntions.Extensions.show
import dev.sanjaygangwar.tempproject.utils.mUtils.mLog

class Setting : BaseFragment<SettingBinding>(SettingBinding::inflate) {

    val args: SettingArgs by navArgs()
    override fun getDataFromTheServer() {
        mLog("DEEPLINK VLAUE IS :: " + args.userID)
    }

    override fun initAllComponents() {
        clickableViews = listOf(bind?.back)
        bind?.apply {
            webView.settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
            }

            // Set a WebViewClient to handle loading of links within the app
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    return false // Load the URL in the WebView itself
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    bind?.progressBar?.show()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    bind?.progressBar?.hide()
                }
            }

            // Set a WebChromeClient to handle web capabilities such as JavaScript alerts
            webView.webChromeClient = object : WebChromeClient() {
                // You can implement custom methods to handle other browser features
            }

            // Load the initial URL
            args.userID?.let { webView.loadUrl(it) }

            // Handle back navigation for WebView
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webView.canGoBack()) {
                        webView.goBack() // Go back to the previous web page
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed() // Handle back press normally
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind?.webView?.destroy()
    }

    override fun initAllObserver() {}

    override fun initOnClickListener() {}

    override fun onViewClicker(p0: View?) {}
    override fun onViewLongClicker(p0: View?) {
        when (p0?.id) {
            R.id.back -> {
                findNavController().popBackStack()
            }
        }
    }

}
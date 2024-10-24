package dev.sanjaygangwar.tempproject.ui.fragment.splash

import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import dev.sanjaygangwar.tempproject.databinding.SplashBinding
import dev.sanjaygangwar.tempproject.utils.baseclasses.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class Splash : BaseFragment<SplashBinding>(SplashBinding::inflate) {

    override fun getDataFromTheServer() {}
    override fun initAllComponents() {
        CoroutineScope(Dispatchers.IO).launch {
            moveToHomeScreen()
        }
    }


    private suspend fun moveToHomeScreen() {
        runBlocking {
            val action = SplashDirections.actionSplashToHome()
            delay(1000)
            withContext(Dispatchers.Main) {
                action.navigate()
            }
        }
    }

    override fun initAllObserver() {}
    override fun initOnClickListener() {}
    override fun onViewClicker(p0: View?) {}
    override fun onViewLongClicker(p0: View?) {

    }

}
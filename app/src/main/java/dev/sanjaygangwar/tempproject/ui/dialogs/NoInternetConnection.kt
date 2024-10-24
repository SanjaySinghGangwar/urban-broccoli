package dev.sanjaygangwar.tempproject.ui.dialogs

import android.view.View
import dev.sanjaygangwar.tempproject.databinding.NoInternetConnectionBinding
import dev.sanjaygangwar.tempproject.utils.baseclasses.BaseDialogFragment

class NoInternetConnection : BaseDialogFragment<NoInternetConnectionBinding>(NoInternetConnectionBinding::inflate) {
    override fun getDataFromTheServer() {}

    override fun initAllComponents() {}

    override fun initAllObserver() {}

    override fun initOnClickListener() {}
    override fun onViewClicker(p0: View?) {}

}
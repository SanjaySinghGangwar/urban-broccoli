package dev.sanjaygangwar.tempproject.ui.fragment.home

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.sanjaygangwar.tempproject.databinding.HomeBinding
import dev.sanjaygangwar.tempproject.utils.baseclasses.BaseFragment
import dev.sanjaygangwar.tempproject.utils.mUtils.mLog

@AndroidEntryPoint
class Home : BaseFragment<HomeBinding>(HomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    override fun getDataFromTheServer() {}
    override fun initAllComponents() {}

    override fun initAllObserver() {}
    override fun initOnClickListener() {}
    override fun onViewClicker(p0: View?) {}

}
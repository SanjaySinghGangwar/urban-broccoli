package dev.sanjaygangwar.tempproject.ui.fragment.setting

import android.view.View
import androidx.navigation.fragment.navArgs
import dev.sanjaygangwar.tempproject.databinding.SettingBinding
import dev.sanjaygangwar.tempproject.utils.baseclasses.BaseFragment
import dev.sanjaygangwar.tempproject.utils.mUtils.mLog

class Setting : BaseFragment<SettingBinding>(SettingBinding::inflate) {

    val args: SettingArgs by navArgs()
    override fun getDataFromTheServer() {
        mLog("DEEPLINK VLAUE IS :: " + args.userID)
    }

    override fun initAllComponents() {}

    override fun initAllObserver() {}

    override fun initOnClickListener() {}

    override fun onViewClicker(p0: View?) {}

}
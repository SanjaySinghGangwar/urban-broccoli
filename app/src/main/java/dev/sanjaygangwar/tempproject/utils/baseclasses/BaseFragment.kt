package dev.sanjaygangwar.tempproject.utils.baseclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import dev.sanjaygangwar.tempproject.ui.activity.Main
import dev.sanjaygangwar.tempproject.utils.HapticFeedbackManager


abstract class BaseFragment<VB : ViewBinding>(private val inflate: InflateFragmentLayout<VB>) :
    Fragment(), View.OnClickListener {

    private var bind: VB? = null
    private val hapticFeedbackManager by lazy { context?.let { HapticFeedbackManager(it) } }
    lateinit var clickableViews: List<View?>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflate.invoke(inflater, container, false).apply { bind = this }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
        initOnClickListener()
        initAllObserver()
        getDataFromTheServer()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        if (::clickableViews.isInitialized) {
            clickableViews.forEach {
                it?.setOnClickListener(this)
            }
        }
    }

    abstract fun getDataFromTheServer()

    abstract fun initAllComponents()
    abstract fun initAllObserver()
    abstract fun initOnClickListener()

    abstract fun onViewClicker(p0: View?)

    override fun onClick(p0: View?) {
        hapticFeedbackManager?.vibrate(100)
        onViewClicker(p0)
    }

    open fun NavDirections.navigate() {
        findNavController().navigate(this)
    }

    override fun onDetach() {
        super.onDetach()
        if (::clickableViews.isInitialized) {
            clickableViews.forEach {
                it?.setOnClickListener(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }

    fun showLoader(){
        (activity as Main).showLoader()
    }

    fun hideLoader(){
        (activity as Main).hideLoader()
    }
}
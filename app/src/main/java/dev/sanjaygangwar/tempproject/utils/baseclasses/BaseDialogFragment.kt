package dev.sanjaygangwar.tempproject.utils.baseclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import dev.sanjaygangwar.tempproject.utils.HapticFeedbackManager


typealias InflateFragmentLayout<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseDialogFragment<VB : ViewBinding>(private val inflate: InflateFragmentLayout<VB>) : DialogFragment(), View.OnClickListener {

    var bind: VB? = null
    private val hapticFeedbackManager by lazy { context?.let { HapticFeedbackManager(it) } }
    lateinit var clickableViews: List<View?>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent);
        return inflate.invoke(inflater, container, false).apply { bind = this }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
        initOnClickListener()
        getDataFromTheServer()
        initAllObserver()
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
    abstract fun initAllObserver()
    abstract fun initAllComponents()
    abstract fun initOnClickListener()

    abstract fun onViewClicker(p0: View?)

    override fun onClick(p0: View?) {
        hapticFeedbackManager?.vibrate(100)
        onViewClicker(p0)
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
}
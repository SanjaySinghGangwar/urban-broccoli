package dev.sanjaygangwar.tempproject.utils.baseclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import dev.sanjaygangwar.tempproject.utils.HapticFeedbackManager

typealias InflateActivityLayout<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB : ViewBinding>(
    private val inflate: InflateActivityLayout<VB>
) : AppCompatActivity(), View.OnClickListener {

    var bind: VB? = null
    private val hapticFeedbackManager by lazy { this?.let { HapticFeedbackManager(it) } }
    lateinit var clickableViews: List<View?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = inflate(layoutInflater)
        bind?.apply {
            setContentView(this.root)
        }
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

    override fun onClick(p0: View?) {
        hapticFeedbackManager?.vibrate(100)
        onViewClicker(p0)
    }

    abstract fun onViewClicker(p0: View?)

    override fun onDestroy() {
        super.onDestroy()
        bind = null
        if (::clickableViews.isInitialized) {
            clickableViews.forEach {
                it?.setOnClickListener(null)
            }
        }
    }
}
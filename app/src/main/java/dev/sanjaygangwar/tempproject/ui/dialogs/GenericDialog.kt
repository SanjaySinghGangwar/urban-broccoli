package dev.sanjaygangwar.tempproject.ui.dialogs

import android.view.View
import dev.sanjaygangwar.tempproject.R
import dev.sanjaygangwar.tempproject.databinding.GenericDialogBinding
import dev.sanjaygangwar.tempproject.models.dataclass.GenericDialogModelClass
import dev.sanjaygangwar.tempproject.utils.baseclasses.BaseDialogFragment

class GenericDialog(
    private val flag: String,
    private val genericDialogModelClass: GenericDialogModelClass,
    private val listener: GenericDialogListener,
) : BaseDialogFragment<GenericDialogBinding>(GenericDialogBinding::inflate) {

    interface GenericDialogListener {
        fun onPositiveClick(flag: String)
        fun onNegativeClick(flag: String)
    }

    override fun getDataFromTheServer() {

    }

    override fun initAllComponents() {
        bind?.message?.text = genericDialogModelClass.message
        bind?.allow?.text = genericDialogModelClass.allowText
        bind?.deny?.text = genericDialogModelClass.denyText
        bind?.lottie?.setAnimation(genericDialogModelClass.lottie)
    }

    override fun initAllObserver() {}

    override fun initOnClickListener() {
        bind?.allow?.setOnClickListener(this)
        bind?.deny?.setOnClickListener(this)
    }

    override fun onViewClicker(p0: View?) {
        when (p0?.id) {
            R.id.allow -> {
                dismiss()
                listener.onPositiveClick(flag)
            }

            R.id.deny -> {
                dismiss()
                listener.onNegativeClick(flag)
            }
        }
    }

}
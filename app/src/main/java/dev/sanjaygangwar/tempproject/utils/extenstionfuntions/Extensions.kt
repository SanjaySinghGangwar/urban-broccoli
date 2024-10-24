package dev.sanjaygangwar.tempproject.utils.extenstionfuntions

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.core.view.drawToBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dev.sanjaygangwar.tempproject.R
import dev.sanjaygangwar.tempproject.utils.mUtils
import jp.wasabeef.glide.transformations.BlurTransformation

object Extensions {
    fun ImageView.loadImageFromUrl(url: String) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image while loading
            .error(R.drawable.error) // Image to show if loading fails
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) // Cache strategy


        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(this)
    }

    fun ImageView.loadImageFromUrlWithBlur(url: String, percent: Int = 5) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image while loading
            .error(R.drawable.error) // Image to show if loading fails
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) // Cache strategy


        Glide.with(context)
            .load(url)
            .apply(RequestOptions().transform(getTransformation(percent)))
            .apply(requestOptions)
            .into(this)
    }

    private fun getTransformation(percent: Int? = null): Transformation<Bitmap?>? {
        percent?.let {
            return BlurTransformation(
                getBlurParametersFromPercent(percent).first,
                getBlurParametersFromPercent(percent).second
            )
        } ?: run {
            return null
        }
    }

    fun getBlurParametersFromPercent(percentBlur: Int): Pair<Int, Int> {
        val radius = (percentBlur * 0.25).toInt()
        val sampling = if (radius >= 4) 4 else 1
        return Pair(radius, sampling)
    }

    fun View.hide() {
        if (this.visibility == View.VISIBLE)
            this.visibility = View.GONE
    }

    fun View.show() {
        if (this.visibility == View.GONE)
            this.visibility = View.VISIBLE
    }

    fun View.getScreenShot(): Bitmap? {
        try {
            return this.drawToBitmap(Bitmap.Config.ARGB_8888)
        } catch (e: java.lang.Exception) {
            mUtils.mLog(e.message.toString())
        }
        return null
    }

}
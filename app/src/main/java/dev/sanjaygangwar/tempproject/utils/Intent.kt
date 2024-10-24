package dev.sanjaygangwar.tempproject.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object Intent {
    fun shareImage(uri: Uri, context: Context, message: String? = null) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        shareIntent.putExtra(Intent.EXTRA_TEXT, message)
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(Intent.createChooser(shareIntent, "Share image using"))
    }
}
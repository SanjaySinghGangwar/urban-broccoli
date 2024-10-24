package dev.sanjaygangwar.tempproject.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FilesUri {
    fun saveBitmapToDownloads(context: Context, bitmap: Bitmap) {
        val downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val fileName = "image_${System.currentTimeMillis()}.png"
        val file = File(downloadsDirectory, fileName)

        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            // Notify the system that a new file was added to the Downloads folder
            MediaStore.Images.Media.insertImage(context.contentResolver, file.absolutePath, fileName, null)

            Toast.makeText(context, "Image saved to Downloads folder", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
        }
    }

    fun bitmapToUri(context: Context, bitmap: Bitmap): Uri? {
        // Get the context's cache directory
        val cachePath = File(context.cacheDir, "images")
        cachePath.mkdirs() // Make sure the directory exists

        // Create a file to save the bitmap
        val file = File(cachePath, "image.png")
        try {
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        // Get the Uri using FileProvider
        return FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
    }
}
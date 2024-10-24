package dev.sanjaygangwar.tempproject.utils

object Formatter {
    fun Float.formatFloat(): String {
        return "%.1f".format(this)
    }
}
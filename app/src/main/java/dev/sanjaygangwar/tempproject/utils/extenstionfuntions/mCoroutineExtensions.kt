package dev.sanjaygangwar.tempproject.utils.mCoroutineExtensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object mCoroutineExtensions {

    fun runOnBackGround(function: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            function()
        }
    }

    fun runOnMain(function: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            function()
        }
    }

    fun runOnDefault(function: () -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            function()
        }
    }

    fun Unconfined(function: () -> Unit) {
        CoroutineScope(Dispatchers.Unconfined).launch {
            function()
        }
    }
}
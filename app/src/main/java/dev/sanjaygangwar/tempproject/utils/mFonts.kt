package dev.sanjaygangwar.tempproject.utils

import dev.sanjaygangwar.tempproject.R
import kotlin.random.Random

object mFonts {

    fun getFontFontForHandWriting(): Int {
        return when (Random.nextInt(10)) {
            0 -> R.font.handwritten0
            1 -> R.font.handwritten1
            2 -> R.font.handwritten2
            3 -> R.font.handwritten3
            4 -> R.font.handwritten4
            5 -> R.font.handwritten5
            6 -> R.font.handwritten6
            7 -> R.font.handwritten7
            8 -> R.font.handwritten8
            9 -> R.font.handwritten9
            10 -> R.font.handwritten10
            11 -> R.font.handwritten11
            else -> R.font.handwritten0
        }
    }
    fun getFontFontForHandName(): Int {
        return when (Random.nextInt(10)) {
            0 -> R.font.name0
            1 -> R.font.name1
            2 -> R.font.name2
            3 -> R.font.name3
            4 -> R.font.name4
            5 -> R.font.name5
            6 -> R.font.name6
            7 -> R.font.name7
            8 -> R.font.name8
            9 -> R.font.name9
            else -> R.font.name0
        }
    }
}
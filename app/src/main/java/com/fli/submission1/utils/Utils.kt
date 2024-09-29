package com.fli.submission1

import android.graphics.Color
import android.os.Build
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.graphics.ColorUtils

class Utils {
    companion object{
        const  val EXTRA_DATA = "extra_data"
        fun changeStatusBarColor(window : Window , color: String) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor(color)
        }
    }
}

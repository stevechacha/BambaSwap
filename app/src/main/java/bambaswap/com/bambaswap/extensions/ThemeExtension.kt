package bambaswap.com.bambaswap.extensions

import android.app.Application
import android.app.UiModeManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

fun Application.changeAppTheme(isDark: Boolean) {
    val uiModeManager = getSystemService(AppCompatActivity.UI_MODE_SERVICE) as UiModeManager

    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (isDark) {
                uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES)
            } else {
                uiModeManager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO)
            }
        }

        else -> {
            if (isDark) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
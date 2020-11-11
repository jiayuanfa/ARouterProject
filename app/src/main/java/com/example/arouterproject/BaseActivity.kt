package com.example.arouterproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * <pre>
 *     author: Jafar
 *     date  : 2020/11/11
 *     desc  :
 * </pre>
 */
class BaseActivity : AppCompatActivity() {

    companion object {
        const val is_application = BuildConfig.is_application
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
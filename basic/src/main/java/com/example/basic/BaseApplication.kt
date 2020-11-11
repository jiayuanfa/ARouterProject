package com.example.basic

import android.app.Application

/**
 * <pre>
 *     author: Jafar
 *     date  : 2020/11/11
 *     desc  :
 * </pre>
 */
open class BaseApplication : Application() {

    companion object {
        const val is_application = BuildConfig.is_application
    }

    override fun onCreate() {
        super.onCreate()

        // 这里可以做一些全局的加载操作
    }
}
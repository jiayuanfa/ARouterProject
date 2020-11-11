package com.example.login

import com.example.arouter.ARouter
import com.example.arouter.IRouter

/**
 * <pre>
 *     author: Jafar
 *     date  : 2020/11/11
 *     desc  :
 *     使用编译时技术、自动生成工具类
 * </pre>
 */
class ActivityUtil : IRouter{

    override fun putActivity() {
        ARouter.getInstance().addActivity("login/login", LoginActivity::class.java)
    }
}
package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.annotation.BindPath
import com.example.arouter.ARouter
import kotlinx.android.synthetic.main.activity_login.*

@BindPath("login/login")
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        jumpActivity.setOnClickListener {
            jumpActivity()
        }
    }

    fun jumpActivity() {
        ARouter.getInstance().jumpActivity("1", Bundle())
    }
}

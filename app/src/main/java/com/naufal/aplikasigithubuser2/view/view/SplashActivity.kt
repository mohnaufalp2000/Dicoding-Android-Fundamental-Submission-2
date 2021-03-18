package com.naufal.aplikasigithubuser2.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import com.naufal.aplikasigithubuser2.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val binding by lazy{ActivitySplashBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finishAffinity()
        },3000)
    }
}
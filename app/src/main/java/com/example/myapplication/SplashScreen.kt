package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        val intent = Intent(this,Menu::class.java)

        val animation = AnimationUtils.loadAnimation(this, R.anim.input_animation)
        animation.duration = 1500

        binding.imgSS0.startAnimation(animation)
        binding.imgSSX.startAnimation(animation)

        Handler().postDelayed(
            Runnable {
                startActivity(intent)
                finish()
            },1600
        )

    }
}
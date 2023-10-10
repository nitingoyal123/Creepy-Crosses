package com.example.myapplication

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.compose.animation.Animatable
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_menu)
        binding.imgMenuVersus.visibility = View.INVISIBLE

        val alpha = AnimationUtils.loadAnimation(this,R.anim.alpha_animation)
        val scale = AnimationUtils.loadAnimation(this,R.anim.input_animation)
        alpha.duration = 1800
        scale.duration = 600

        binding.imgMenu0.startAnimation(alpha)
        binding.imgMenuX.startAnimation(alpha)

        Handler().postDelayed(Runnable {
            binding.imgMenuVersus.visibility = View.VISIBLE
            binding.imgMenuVersus.startAnimation(scale)
        },1200)


    }


    fun play(view : View) {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun aboutGame(view : View) {
        val intent = Intent(this,AboutGame::class.java)
        startActivity(intent)
    }
}
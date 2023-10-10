package com.example.myapplication

import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.Animation
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private var turn = 0
    private lateinit var binding : ActivityMainBinding
    var arr = ArrayList<Check>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        arr.add(Check(0,5))
        arr.add(Check(R.id.img1,-1))
        arr.add(Check(R.id.img2,-1))
        arr.add(Check(R.id.img3,-1))
        arr.add(Check(R.id.img4,-1))
        arr.add(Check(R.id.img5,-1))
        arr.add(Check(R.id.img6,-1))
        arr.add(Check(R.id.img7,-1))
        arr.add(Check(R.id.img8,-1))
        arr.add(Check(R.id.img9,-1))
        reset(binding.resetGame)
    }

    fun setImages(view : View) {
        var id = view.id
        var origView = findViewById<ImageView>(id)
        var index = 0
        for (i in 1..9) {
            if (arr[i].id == id) {
                index = i
            }
        }
        if (arr[index].status == -1) {
            if (turn == 0) {
                origView.setImageResource(R.drawable.halloween0)
                animateInput(arr[index].id)
                arr[index].status = 0
                turn = 1
                binding.imgTurn.setImageResource(R.drawable.hat)
            } else {
                origView.setImageResource(R.drawable.hat)
                animateInput(arr[index].id)
                arr[index].status = 1
                turn = 0
                binding.imgTurn.setImageResource(R.drawable.halloween0)
            }
        }

        checkStatus()
    }

    fun checkStatus() {
        if (arr[1].status == arr[2].status && arr[2].status == arr[3].status) {
            if (arr[1].status == 0) {
                showToast(0)
                animateResults(arr[1].id,arr[2].id,arr[3].id)
            } else if (arr[1].status == 1) {
                showToast(1)
                animateResults(arr[1].id,arr[2].id,arr[3].id)
            }
        }
        if (arr[4].status == arr[5].status && arr[5].status == arr[6].status) {
            if (arr[4].status == 0) {
                showToast(0)
                animateResults(arr[4].id,arr[5].id,arr[6].id)
            } else if (arr[4].status == 1) {
                showToast(1)
                animateResults(arr[4].id,arr[5].id,arr[6].id)
            }
        }
        if (arr[7].status == arr[8].status && arr[8].status == arr[9].status) {
            if (arr[7].status == 0) {
                showToast(0)
                animateResults(arr[7].id,arr[8].id,arr[9].id)
            } else if (arr[7].status == 1) {
                showToast(1)
                animateResults(arr[7].id,arr[8].id,arr[9].id)
            }
        }
        if (arr[1].status == arr[4].status && arr[4].status == arr[7].status) {
            if (arr[1].status == 0) {
                showToast(0)
                animateResults(arr[1].id,arr[4].id,arr[7].id)
            } else if (arr[1].status == 1) {
                showToast(1)
                animateResults(arr[1].id,arr[4].id,arr[7].id)
            }
        }
        if (arr[2].status == arr[5].status && arr[5].status == arr[8].status) {
            if (arr[2].status == 0) {
                showToast(0)
                animateResults(arr[2].id,arr[5].id,arr[8].id)
            } else if (arr[2].status == 1) {
                showToast(1)
                animateResults(arr[2].id,arr[5].id,arr[8].id)
            }
        }
        if (arr[3].status == arr[6].status && arr[6].status == arr[9].status) {
            if (arr[3].status == 0) {
                showToast(0)
                animateResults(arr[3].id,arr[6].id,arr[9].id)
            } else if (arr[3].status == 1) {
                showToast(1)
                animateResults(arr[3].id,arr[6].id,arr[9].id)
            }
        }
        if (arr[1].status == arr[5].status && arr[5].status == arr[9].status) {
            if (arr[1].status == 0) {
                showToast(0)
                animateResults(arr[1].id,arr[5].id,arr[9].id)
            } else if (arr[1].status == 1){
                showToast(1)
                animateResults(arr[1].id,arr[5].id,arr[9].id)
            }
        }
        if (arr[3].status == arr[5].status && arr[5].status == arr[7].status) {
            if (arr[3].status == 0) {
                showToast(0)
                animateResults(arr[3].id,arr[5].id,arr[7].id)
            } else if (arr[3].status == 1) {
                showToast(1)
                animateResults(arr[3].id,arr[5].id,arr[7].id)
            }
        }

            var checkAll = true
            for (i in 1..9) {
                if (arr[i].status == -1) {
                    checkAll = false
                }
            }
            if (checkAll) {
                Handler().postDelayed(Runnable {
                    Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show()
                    reset(binding.resetGame)
                },500)
            }

    }

    fun reset(view : View) {
        turn = 0
        for (i in 1..9) {
            findViewById<ImageView>(arr[i].id).setImageDrawable(null)
            arr[i].status = -1
        }
        binding.imgTurn.setImageResource(R.drawable.halloween0)
    }

    fun animateResults(a : Int,b : Int, c : Int) {

        val animation = AnimationUtils.loadAnimation(this,R.anim.win_animation)
        findViewById<ImageView>(a).startAnimation(animation)
        findViewById<ImageView>(b).startAnimation(animation)
        findViewById<ImageView>(c).startAnimation(animation)

        Handler().postDelayed(
            Runnable {
                reset(binding.resetGame)
            },1000
        )
    }


    fun animateInput(id : Int) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.input_animation)
        animation.duration = 500
        animation.repeatCount = 0

        findViewById<ImageView>(id).startAnimation(animation)
    }

    fun showToast(whoWin : Int) {

        var imageId : Int
        var winner : String

        if (whoWin == 0) {
            winner = "0"
            imageId = R.drawable.halloween0
        } else {
            winner = "X"
            imageId = R.drawable.hat
        }
        val toast = Toast.makeText(this,"$winner Win",Toast.LENGTH_SHORT).show()
    }
}
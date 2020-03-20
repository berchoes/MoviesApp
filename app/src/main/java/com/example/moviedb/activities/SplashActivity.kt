package com.example.moviedb.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedb.R

@SuppressLint("Registered")
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       object : CountDownTimer(2000,1000){

           override fun onFinish() {
               val intent = Intent(applicationContext,
                   LoginActivity::class.java)
               startActivity(intent)
               finish()
           }
           override fun onTick(millisUntilFinished: Long) {
               println("CountDown")
           }

       }.start()
    }




}
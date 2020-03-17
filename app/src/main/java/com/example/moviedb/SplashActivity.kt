package com.example.moviedb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

@SuppressLint("Registered")
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       object : CountDownTimer(2000,1000){

           override fun onFinish() {
               val intent = Intent(applicationContext,LoginActivity::class.java)
               startActivity(intent)
               finish()
           }
           override fun onTick(millisUntilFinished: Long) {
               println("CountDown")
           }

       }.start()
    }




}
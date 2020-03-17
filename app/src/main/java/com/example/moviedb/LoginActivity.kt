package com.example.moviedb

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = this.getSharedPreferences("com.example.moviedb", Context.MODE_PRIVATE )

        val emailPref = sharedPreferences.getString("email", null)
        val pwPref = sharedPreferences.getString("password", null)
        val isButtonChecked : Boolean = sharedPreferences.getBoolean("checkbox", false)
        editText?.setText(emailPref)
        editText1?.setText(pwPref)
        checkBox.setChecked(isButtonChecked)


        checkBox.setOnCheckedChangeListener { buttonView, _ ->

            if(buttonView.isChecked){
                val email = editText.text.toString()
                val password = editText1.text.toString()
                sharedPreferences.edit().putString("email",email).apply()
                sharedPreferences.edit().putString("password",password).apply()
                sharedPreferences.edit().putBoolean("checkbox", checkBox.isChecked()).apply()


            }else if(!buttonView.isChecked){
                sharedPreferences.edit().clear().apply()
            }

        }

    }

    fun login(view: View){

        val intent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(intent)
        finish()

    }
}



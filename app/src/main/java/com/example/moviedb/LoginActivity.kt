package com.example.moviedb

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val email ="b"
    private val pw ="1"

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

        if(editText?.text!!.equals(email) && editText1.text.equals(pw)){

            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()

        }else{
            Toast.makeText(this,"Wrong E-mail or Password!!", Toast.LENGTH_SHORT).show()

        }



    }
}



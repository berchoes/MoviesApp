package com.example.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviedb.fragments.HomeFragment
import com.example.moviedb.fragments.ProfileFragment
import com.example.moviedb.services.MovieModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*


//http://www.omdbapi.com/?i=tt3896198&apikey=39a81601


class HomeActivity : AppCompatActivity() {

    private val mOnnavigationItemSelectedListener =

        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    println("home pressed")
                    replaceFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    println("profile pressed")
                    replaceFragment(ProfileFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavi.setOnNavigationItemSelectedListener(mOnnavigationItemSelectedListener)

        replaceFragment(HomeFragment())




    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}
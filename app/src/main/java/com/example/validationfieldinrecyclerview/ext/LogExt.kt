package com.example.validationfieldinrecyclerview.ext

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log


fun Log.e(arg : String){
    Log.e("shohiebsense ",arg)
}

fun Log.default(activity : AppCompatActivity, arg : String){
    Log.e(activity::class.java.name,arg)
}

fun Log.default(fragment : Fragment, arg : String){
    Log.e(fragment::class.java.name,arg)
}





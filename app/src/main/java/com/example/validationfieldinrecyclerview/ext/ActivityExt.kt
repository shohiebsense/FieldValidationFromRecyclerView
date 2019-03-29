package com.example.validationfieldinrecyclerview.ext

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log

inline fun FragmentManager.doTransaction(func: FragmentTransaction.() -> FragmentTransaction){
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.fragmentAdd(fragment: Fragment, frameId : Int, tag : String? = null){
    supportFragmentManager.doTransaction { add(frameId, fragment, tag) }
}

fun AppCompatActivity.replaceFragment(fragment : Fragment, frameId: Int, tag : String? = null, addToBackStack : Boolean = false){
    supportFragmentManager.doTransaction {
        if(addToBackStack){
            addToBackStack(null)
        }
        replace(frameId, fragment, tag)
    }
}

fun Fragment.log(arguments : String){
    Log.e(this::class.java.name, arguments)
}




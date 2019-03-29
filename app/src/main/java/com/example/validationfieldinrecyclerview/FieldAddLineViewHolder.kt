package com.example.validationfieldinrecyclerview

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.item_add_line.view.*
import kotlinx.android.synthetic.main.item_field.view.*
import java.lang.NumberFormatException

class FieldAddLineViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
     var button_add_line : Button

    lateinit var fieldListener : Field.(Int) -> Unit
    lateinit var field : Field

    companion object {
        fun newInstance(parent : ViewGroup) : FieldAddLineViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_add_line, parent, false)
            return FieldAddLineViewHolder(view)
        }
    }

    init {
       button_add_line = view.button_add_line
    }

    fun bind(listener : () -> Unit){
        button_add_line.setOnClickListener {
            listener()
        }
    }

    fun disable(){
        button_add_line.isEnabled = false
        button_add_line.visibility = View.GONE
    }

    fun enable(){
        button_add_line.isEnabled = true
        button_add_line.visibility = View.VISIBLE
    }


}
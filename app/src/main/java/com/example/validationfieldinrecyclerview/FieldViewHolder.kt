package com.example.validationfieldinrecyclerview

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.item_field.view.*
import java.lang.NumberFormatException

class FieldViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
    var edit_field_1 : EditText
    var edit_field_2 : EditText
    var text_no : TextView
    var button_delete : Button

    lateinit var fieldListener : Field.(Int) -> Unit
    lateinit var field : Field

    companion object {
        fun newInstance(parent : ViewGroup) : FieldViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_field, parent, false)
            return FieldViewHolder(view)
        }
    }

    init {
        edit_field_1 = view.edit_text_field_1
        edit_field_2 = view.edit_text_field_2
        button_delete = view.button_delete
        text_no = view.text_no
    }

    fun setListener(fieldListener : Field.(Int) -> Unit){
        this.fieldListener = fieldListener
    }

    fun bind(position : Int, field: Field, onDeleteButtonClicked : () -> Unit){
        this.field = field
        setListenerOnField()
        text_no.setText((position+1).toString())
        button_delete.setOnClickListener {
            onDeleteButtonClicked()
        }
    }

    fun setListenerOnField(){
        edit_field_1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               validateField(s, Field.Properties.LISTENER_ON_FIELD_1_CHANGED)
            }
        })

        edit_field_2.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateField(s, Field.Properties.LISTENER_ON_FIELD_2_CHANGED)
            }

        })

    }

    fun validateField(s : CharSequence?, properties : Int){
        if(s.isNullOrBlank()){
            field.isEmpty = true
            return
        }
        try {
            val number = s.toString().toLong()
            when(properties){
                Field.Properties.LISTENER_ON_FIELD_1_CHANGED -> field.fieldTotal1 = number
                Field.Properties.LISTENER_ON_FIELD_2_CHANGED -> field.fieldTotal2 = number
            }
            field.isEmpty = false
            field.fieldListener(properties)
        }catch (e: NumberFormatException){

        }
    }



}
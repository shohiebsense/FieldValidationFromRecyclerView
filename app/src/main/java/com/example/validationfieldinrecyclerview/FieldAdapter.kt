package com.example.validationfieldinrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.validationfieldinrecyclerview.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.item_field.view.*
import java.lang.IndexOutOfBoundsException

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class FieldAdapter(
    val fieldListener: Field.(Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val fieldList: ArrayList<Field>
    var isAddlineTriggered = false

    init {
        fieldList = arrayListOf(Field())
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount-1 -> R.layout.item_add_line
            else -> R.layout.item_field
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.item_add_line -> FieldAddLineViewHolder.newInstance(parent)
            else -> FieldViewHolder.newInstance(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position == itemCount-1){
            var holder = (holder as FieldAddLineViewHolder)
            holder.bind {
                if(fieldList.size > 5) return@bind
                isAddlineTriggered = true
                fieldList.add(Field())
                notifyItemInserted(fieldList.size)
            }
            if(fieldList.size > 5){
                holder.disable()
            }
            else{
                holder.enable()
            }
        }
        else{
            val item = fieldList[position]
            var holder = (holder as FieldViewHolder)
            holder.setListener(fieldListener)
            holder.bind(position, item){
                isAddlineTriggered = false
                try{
                    fieldList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(0, itemCount)
                }catch (e : IndexOutOfBoundsException){
                }
            }
        }
    }

    override fun getItemCount(): Int = fieldList.size + 1

}

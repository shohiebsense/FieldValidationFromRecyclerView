package com.example.validationfieldinrecyclerview

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.validationfieldinrecyclerview.dummy.DummyContent
import com.example.validationfieldinrecyclerview.dummy.DummyContent.DummyItem
import com.example.validationfieldinrecyclerview.ext.log
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [FieldFragment.OnListFragmentInteractionListener] interface.
 */
class FieldFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    var fieldTotal1 = 0L
    var fieldTotal2 = 0L
    lateinit var adapter : FieldAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_field.layoutManager = LinearLayoutManager(activity)
        adapter = FieldAdapter{properties ->
            when(properties){
                Field.Properties.LISTENER_ON_FIELD_1_CHANGED -> {
                    sumTotalField1()
                }
                Field.Properties.LISTENER_ON_FIELD_2_CHANGED -> {
                    sumTotalField2()
                }
            }
        }
        recycler_field.adapter = adapter
    }

    fun sumTotalField1(){
        fieldTotal1 = 0L
        for (field in adapter.fieldList){
            fieldTotal1 += field.fieldTotal1
        }
        log("field1 Total "+fieldTotal1)
    }

    fun sumTotalField2(){
        fieldTotal2 = 0L
        for (field in adapter.fieldList){
            fieldTotal2 += field.fieldTotal2
        }
        log("field2 Total "+fieldTotal2)
    }

    fun validate() {
        for(field in adapter.fieldList){
            if(field.isEmpty){
                view?.let {
                    Snackbar.make(it, "Empty Woi", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                return
            }
        }
        view?.let {
            Snackbar.make(it, "Validated", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int = 0) =
            FieldFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}

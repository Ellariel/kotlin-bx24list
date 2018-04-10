package com.example.lex.bx24list

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.jetbrains.anko.sdk25.coroutines.onClick

class DataViewHolder(
        val view: DataView
) : RecyclerView.ViewHolder(view)

class DataAdapter(private val dataList: DataList
) : RecyclerView.Adapter<DataViewHolder>() {

    override fun getItemCount() = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = DataView(parent.context)
        val width = RecyclerView.LayoutParams.MATCH_PARENT
        val height = dpToPx(80, parent.context)
        view.layoutParams = RecyclerView.LayoutParams(width, height)
        val holder = DataViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataView = holder.view
        val item = dataList[position]
        dataView.bind(item)
        /*menuView.onClick {
            val context = it?.context
            val showDataIntent = Intent(context, DataActivity::class.java)
            showDataIntent.putExtra("what", position)
            context?.startActivity(showDataIntent)
            Toast.makeText(menuView.context,item,Toast.LENGTH_SHORT).show()
        }*/
    }
}
package com.example.lex.bx24list

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainViewHolder(
        val view: MainView
) : RecyclerView.ViewHolder(view)

fun dpToPx(dp: Int, context: Context): Int {
    val density = context.getResources()
            .getDisplayMetrics()
            .density
    return Math.round(dp.toFloat() * density)
}

class MainAdapter(private val menu: ArrayList<String>
) : RecyclerView.Adapter<MainViewHolder>() {

   //private val menu = arrayListOf ("1", "2", "3")
    override fun getItemCount() = menu.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = MainView(parent.context)
        val width = RecyclerView.LayoutParams.MATCH_PARENT
        val height = dpToPx(80, parent.context)
        view.layoutParams = RecyclerView.LayoutParams(width, height)
        val holder = MainViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val menuView = holder.view
        val item = menu[position]
        menuView.bind(item)
        menuView.onClick {
            val context = it?.context
            val showDataIntent = Intent(context, DataActivity::class.java)
            showDataIntent.putExtra("what", position)
            context?.startActivity(showDataIntent)
            Toast.makeText(menuView.context,item,Toast.LENGTH_SHORT).show()
        }
    }
}
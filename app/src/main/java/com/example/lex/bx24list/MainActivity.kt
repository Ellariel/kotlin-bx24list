package com.example.lex.bx24list

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.textChangedListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainView = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter(arrayListOf ("Сделки", "Контакты", "Продукты"))
            addItemDecoration(DividerItemDecoration(context,LinearLayout.VERTICAL))
        }

        setContentView(mainView)

    }
}

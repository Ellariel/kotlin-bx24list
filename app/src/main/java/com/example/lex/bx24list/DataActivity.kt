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
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class DataActivity : AppCompatActivity() {
    private var selectedData: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataList = DataList ()

        val linearLayout = LinearLayout(this).apply {
            backgroundColor = Color.rgb(200,200,200)
            orientation = LinearLayout.VERTICAL
            title = "bx24list"
        }

        val searchData = EditText(this).apply {
            hint = "Найти..."
            backgroundColor = Color.rgb(220,220,220)
            padding = dip(10)

        }

        selectedData = intent.getIntExtra("what",0)


        val dataView = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DataAdapter(dataList)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }


        Thread({
            //launch(UI) {
            dataList.clear()
            if (selectedData==0) loadDeals().forEach {dataList.add(Data(it.ID,it.TITLE + "\n" + it.OPPORTUNITY + " " + it.CURRENCY_ID,Icon("","")))}
            if (selectedData==1) loadContacts().forEach {dataList.add(Data(it.ID,it.LAST_NAME + "  " + it.NAME + " " + it.SECOND_NAME,Icon("","")))}
            if (selectedData==2) loadProducts().forEach {dataList.add(Data(it.ID,it.NAME + "\n" + it.PRICE + " " + it.CURRENCY_ID,Icon("","")))}
            runOnUiThread {
                dataView.adapter.notifyDataSetChanged()
            }
        }).start()



        searchData.textChangedListener {
            this.afterTextChanged {
                if (it?.isBlank() == false) {

                    dataList.sortedWith(compareBy({ it.TEXT.contains(searchData.text) }, { it.TEXT.length }))

                    dataView.adapter.notifyDataSetChanged()
                }

            }

        }

        linearLayout.addView(searchData)
        linearLayout.addView(dataView)
        setContentView(linearLayout)
/*

        launch(UI) {
            // Получаем список фото
            val cachedData = loadDataFromCache(application as App).await()
            if (cachedData.isNotEmpty()) {
                dataList.clear()
                dataList.addAll(cachedData)
                dataView.adapter.notifyDataSetChanged()
            } else {
                val cloudDataJob = loadDataFromCloud()
                cloudDataJob.start()
                val cloudData= cloudDataJob.await()
                //saveData(application as App, cloudData)
                dataList.clear()
                dataList.addAll(cloudData)
                dataView.adapter.notifyDataSetChanged()
            }
        }

*/


    }
}
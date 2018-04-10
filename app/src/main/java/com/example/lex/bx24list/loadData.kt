package com.example.lex.bx24list

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlin.coroutines.experimental.CoroutineContext

fun loadDeals(): Deals{ //https://b24-vkqv76.bitrix24.ru/rest/1/r4ynraz9cs2k6g04/crm.product.list/  y=${URLEncoder.encode(title,"utf-8")}
    val httpClient = OkHttpClient()
    val request = Request.Builder()
            .url("https://b24-vkqv76.bitrix24.ru/rest/1/r4ynraz9cs2k6g04/crm.deal.list/")
            .build()
    val response = httpClient.newCall(request).execute()
    val obj = JsonParser().parse(response.body()?.string())
    val text = obj.asJsonObject.get("result") ?: JsonObject()
    val deals: Deals = Gson().fromJson(text,Deals::class.java)
    Log.v("http",deals.toString())
    return deals
}

fun loadContacts(): Contacts{ //https://b24-vkqv76.bitrix24.ru/rest/1/r4ynraz9cs2k6g04/crm.product.list/  y=${URLEncoder.encode(title,"utf-8")}
    val httpClient = OkHttpClient()
    val request = Request.Builder()
            .url("https://b24-vkqv76.bitrix24.ru/rest/1/r4ynraz9cs2k6g04/crm.contact.list/")
            .build()
    val response = httpClient.newCall(request).execute()
    val obj = JsonParser().parse(response.body()?.string())
    val text = obj.asJsonObject.get("result") ?: JsonObject()
    val contacts: Contacts = Gson().fromJson(text,Contacts::class.java)
    Log.v("http",contacts.toString())
    return contacts
}

fun loadProducts(): Products{ //https://b24-vkqv76.bitrix24.ru/rest/1/r4ynraz9cs2k6g04/crm.product.list/  y=${URLEncoder.encode(title,"utf-8")}
    val httpClient = OkHttpClient()
    val request = Request.Builder()
            .url("https://b24-vkqv76.bitrix24.ru/rest/1/r4ynraz9cs2k6g04/crm.product.list/")
            .build()
    val response = httpClient.newCall(request).execute()
    val obj = JsonParser().parse(response.body()?.string())
    val text = obj.asJsonObject.get("result") ?: JsonObject()
    val products: Products = Gson().fromJson(text,Products::class.java)
    Log.v("http",products.toString())
    return products
}

fun loadDataFromCloud(
        coroutineContext: CoroutineContext = CommonPool
): Deferred<List<Data>> = async(coroutineContext) {

    val httpClient = OkHttpClient()
    val request = Request.Builder()
            .url("http://jsonplaceholder.typicode.com/photos")
            .build()

    httpClient.newCall(request).execute().use {
        Gson().fromJson(it.body()!!.string(), Data.List::class.java)
    }
}

fun loadDataFromCache(
        app: App,
        coroutineContext: CoroutineContext = CommonPool
): Deferred<List<Data>> = async(coroutineContext) {
    app.database.DataDao().getAll()
}
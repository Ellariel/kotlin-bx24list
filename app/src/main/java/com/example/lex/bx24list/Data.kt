package com.example.lex.bx24list

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.PrimaryKey

@Entity
data class Icon (
        val url: String,
        @PrimaryKey
        val thumbnailUrl: String
){
    class List : ArrayList<Icon>()
}

@Entity
class Data (
        val ID: Int,
        @PrimaryKey
        val TEXT: String,
        @Embedded
        val ICON: Icon
){
    class List : ArrayList<Data>()
}

data class Contact(
        val ID: Int,
        val NAME: String,
        val SECOND_NAME: String,
        val LAST_NAME: String
)

data class Deal(
        val ID: Int,
        val TITLE: String,
        val STAGE_ID: String,
        val CURRENCY_ID: String,
        val OPPORTUNITY: String
)

data class Product(
        val ID: Int,
        val NAME: String,
        val PRICE: String,
        val CURRENCY_ID: String
)

class Contacts : ArrayList<Contact>()
class Products : ArrayList<Product>()
class Deals : ArrayList<Deal>()
class DataList : ArrayList<Data>()
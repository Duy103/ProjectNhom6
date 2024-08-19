package com.example.project1763.Helper

import android.content.Context
import android.widget.Toast
import com.example.project1763.Model.ItemsModel
import com.google.firebase.database.FirebaseDatabase


class ManagmentWishlist(val context: Context) {

    private val tinyDB = TinyDB(context)
    val dbRef = FirebaseDatabase.getInstance().getReference("Items")
    fun insertWish(item: ItemsModel) {
        var listwish = getListWish()
        val existAlready = listwish.any { it.title == item.title }
        item.wish = 1;
        val updatedData = HashMap<String, Any>()
        updatedData["wish"] = 1


        if (!existAlready) {
            listwish.add(item)
        }
        tinyDB.putListObject("WishList", listwish)
    }

    fun getListWish(): ArrayList<ItemsModel> {
        return tinyDB.getListObject("WishList") ?: arrayListOf()
    }

    fun removeItem(item: ItemsModel) {
        var listwish = getListWish()
        listwish.remove(item)
        item.wish = 0;
        val updatedData = HashMap<String, Any>()
        updatedData["wish"] = 0
        tinyDB.putListObject("WishList", listwish)
    }



}
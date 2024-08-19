package com.example.project1763.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project1763.Model.ItemsModel
import com.example.project1763.Model.Order
import com.example.project1763.Model.Profile
import com.example.project1763.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OrderActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val dbRef = database.getReference("Order")
    var mylist = mutableListOf<String>()
    lateinit var lv: ListView
    lateinit var myadapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myadapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mylist)
        getdata(mylist)
        findViewById<ImageView>(R.id.backBtn).setOnClickListener { finish() }
    }

    private fun getdata( list: MutableList<String>) {
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        val list = childSnapshot.getValue(String::class.java)
                        if (list != null) {
                            mylist.add(list.toString())
                            lv = findViewById<ListView>(R.id.lv)

                            lv.setAdapter(myadapter)
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
}
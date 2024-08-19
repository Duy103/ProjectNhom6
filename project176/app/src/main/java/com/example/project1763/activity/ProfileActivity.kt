package com.example.project1763.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project1763.Model.Profile
import com.example.project1763.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    private lateinit var btnupdateprofile : Button
    private lateinit var edtsdt : EditText
    private lateinit var edtdiachi : EditText
    private lateinit var btnback : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        dbRef = FirebaseDatabase.getInstance().getReference("Profile")
        btnupdateprofile = findViewById(R.id.btnUpdateprofile)
        edtsdt = findViewById(R.id.edtSdt)
        edtdiachi = findViewById(R.id.edtDiachi)
        btnback = findViewById(R.id.backBtn)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Profile/sdt")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val sdt = dataSnapshot.getValue(String::class.java)
                edtsdt.setText(sdt)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        val myRef1 = database.getReference("Profile/diachi")

        myRef1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val diachi = dataSnapshot.getValue(String::class.java)
                edtdiachi.setText(diachi)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        btnupdateprofile.setOnClickListener{
            updateprofile()
        }

        btnback.setOnClickListener{
            finish()
        }
    }

    private fun updateprofile() {
        var sdt = edtsdt.text.toString()
        var diachi = edtdiachi.text.toString()

        val profile = Profile(sdt, diachi)
        dbRef.setValue(profile).addOnCompleteListener{
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener{
                Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show()
            }

        if(edtsdt.text.toString()==""){
            edtsdt.error="Chưa nhập số điện thoại"
        }
        if(edtdiachi.text.toString()==""){
            edtdiachi.error="Chưa nhập địa chỉ"
        }
    }
}
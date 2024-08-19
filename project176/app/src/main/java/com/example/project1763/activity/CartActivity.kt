package com.example.project1763.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1763.Helper.ChangeNumberItemsListener
import com.example.project1763.Helper.ManagmentCart
import com.example.project1763.Adapter.CartAdapter
import com.example.project1763.Model.Profile
import com.example.project1763.databinding.ActivityCartBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVariable()
        initCartList()
        taodonhang()
        calculateCart()
    }

    private fun taodonhang() {
        var dbref = FirebaseDatabase.getInstance().getReference("Order")
        binding.btnTaodonhang.setOnClickListener(){
            val managementCart = ManagmentCart(this)
            var data : String = ""

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Profile/sdt")

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val sdt = dataSnapshot.getValue(String::class.java)
                    data = "Số điện thoại: " + sdt

                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

            val myRef1 = database.getReference("Profile/diachi")
            val intent = Intent(this, OrderActivity::class.java)
            myRef1.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val diachi = dataSnapshot.getValue(String::class.java)
                    data += "__Địa chỉ: " + diachi
                    var tonggia: Double = 0.0
                    managementCart.getListCart().forEach {
                        tonggia = tonggia + it.price * it.numberInCart
                        data += "__Tên sản phẩm: " + it.title + "__Số lượng: " + it.numberInCart + "__Giá: " + it.price
                    }
                    data += "__Tổng giá: " + tonggia
                    dbref.child(dbref.push().key!!).setValue(data)
                    startActivity(intent)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
    }

    private fun initCartList() {
        binding.viewCart.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewCart.adapter =
            CartAdapter(managmentCart.getListCart(), this, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }
            })

        with(binding) {
            emptyTxt.visibility =
                if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility =
                if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100

        with(binding) {
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}
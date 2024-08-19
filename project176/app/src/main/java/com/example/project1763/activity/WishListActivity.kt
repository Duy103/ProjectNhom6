package com.example.project1763.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1763.Adapter.CartAdapter
import com.example.project1763.Adapter.WishAdapter
import com.example.project1763.Helper.ChangeNumberItemsListener
import com.example.project1763.Helper.ManagmentCart
import com.example.project1763.Helper.ManagmentWishlist
import com.example.project1763.R
import com.example.project1763.databinding.ActivityCartBinding
import com.example.project1763.databinding.ActivityWishListBinding
import com.example.project1763.databinding.ViewholderWishBinding

class WishListActivity : BaseActivity() {
    private lateinit var binding: ActivityWishListBinding
    private lateinit var managmentWishlist: ManagmentWishlist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentWishlist = ManagmentWishlist(this)

        setVariable()
        initwishList()

    }

    private fun initwishList() {
        binding.viewWish.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewWish.adapter =
            WishAdapter(managmentWishlist.getListWish(), this)

        with(binding) {
            emptyTxt.visibility =
                if (managmentWishlist.getListWish().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility =
                if (managmentWishlist.getListWish().isEmpty()) View.GONE else View.VISIBLE
        }
    }


    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}
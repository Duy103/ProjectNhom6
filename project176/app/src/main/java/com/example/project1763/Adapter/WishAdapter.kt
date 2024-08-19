package com.example.project1763.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.project1763.Adapter.CartAdapter.ViewHolder
import com.example.project1763.Helper.ChangeNumberItemsListener
import com.example.project1763.Helper.ManagmentCart
import com.example.project1763.Helper.ManagmentWishlist
import com.example.project1763.Model.ItemsModel
import com.example.project1763.databinding.ViewholderCartBinding
import com.example.project1763.databinding.ViewholderWishBinding

class WishAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context): RecyclerView.Adapter<WishAdapter.ViewHolder>(){
    class ViewHolder(val binding: ViewholderWishBinding):RecyclerView.ViewHolder(binding.root) {

    }

    private val managementwishlist = ManagmentWishlist(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishAdapter.ViewHolder {
        val binding =
            ViewholderWishBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishAdapter.ViewHolder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItem.text = "$${item.price}"
        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)
        if(item.wish==1){
            holder.binding.favBtn.isChecked = true
        }
        holder.binding.favBtn.setOnClickListener {
            if(holder.binding.favBtn.isChecked){
                managementwishlist.insertWish(listItemSelected[position])

            }
            else{
                managementwishlist.removeItem(listItemSelected[position])

            }

        }

    }

    override fun getItemCount(): Int = listItemSelected.size


}
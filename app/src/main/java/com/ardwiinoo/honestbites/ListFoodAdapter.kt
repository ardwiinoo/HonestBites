package com.ardwiinoo.honestbites

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListFoodAdapter(private val listFood: ArrayList<Food>) : RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_food, parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo, _) = listFood[position]
        holder.tvFoodName.text = name
        holder.tvFoodDescription.text = description
        holder.imgFoodPhoto.setImageResource(photo)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_FOOD, listFood[holder.adapterPosition])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listFood.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFoodPhoto: ImageView = itemView.findViewById(R.id.img_item_food_photo)
        val tvFoodName: TextView = itemView.findViewById(R.id.tv_item_food_name)
        val tvFoodDescription: TextView = itemView.findViewById(R.id.tv_item_food_description)
    }

}
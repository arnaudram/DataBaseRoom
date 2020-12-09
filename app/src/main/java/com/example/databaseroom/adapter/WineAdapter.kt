package com.example.databaseroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseroom.R
import com.example.databaseroom.models.Wine

class WineAdapter:androidx.recyclerview.widget.ListAdapter<Wine,WineAdapter.ItemWineHolder>(WINE_COMPARATOR) {
    class ItemWineHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(item: Wine?) {
            item?.let {
                itemView.findViewById<TextView>(R.id.item_name).text=it.name
                itemView.findViewById<TextView>(R.id.item_alc_level).text=it.alc_level.toString()
                itemView.findViewById<TextView>(R.id.item_price).text=it.price.toString()
            }
        }

    }

    object WINE_COMPARATOR : DiffUtil.ItemCallback<Wine>()
    {
        override fun areItemsTheSame(oldItem: Wine, newItem: Wine): Boolean {
            return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(oldItem: Wine, newItem: Wine): Boolean {
           return oldItem==newItem
        }

    }

    override fun onBindViewHolder(holder: ItemWineHolder, position: Int) {
        val item= getItem(position)
        holder.bindTo(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemWineHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.item_wine,parent, false )
        return ItemWineHolder(view)
    }
}
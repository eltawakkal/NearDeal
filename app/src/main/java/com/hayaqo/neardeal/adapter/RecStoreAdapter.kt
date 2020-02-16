package com.hayaqo.neardeal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hayaqo.neardeal.R
import com.hayaqo.neardeal.model.Store
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_stores.view.*

class RecStoreAdapter(val context: Context?, val listStore: MutableList<Store>) : RecyclerView.Adapter<RecStoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.row_stores, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listStore.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvStoreName.text = listStore.get(position).name
        holder.tvStoreDesk.text = listStore.get(position).description

        Picasso.get().load(listStore.get(position).photo)
            .into(holder.imgStore)

        holder.imgStore.setOnClickListener {
            Toast.makeText(
                context,
                "Address: ${listStore.get(position).address} " +
                        "\nTelp: ${listStore.get(position).telp}",
                Toast.LENGTH_SHORT).show()
        }

        holder.imgDelete.setOnClickListener {
            listStore.removeAt(position)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStoreName = view.tv_list_store_name
        val tvStoreDesk = view.tv_list_store_desk
        val imgStore = view.img_list_store_list
        val imgDelete = view.img_delete_row_store
    }
}
package com.example.cleanarchmvvmlist.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchmvvmlist.data.dto.Item
import com.example.cleanarchmvvmlist.databinding.ItemBinding

class ItemListAdapter(private val listener: ItemListener) : RecyclerView.Adapter<ItemListViewHolder>()  {
    interface ItemListener {
        fun onClickItem(itemId:Int)

    }

    private val items = ArrayList<Item>()

    fun setItems(items: ArrayList<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val binding: ItemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(binding, listener)

    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int =items.size
}

class ItemListViewHolder(private val itemBinding: ItemBinding,
                         private val listener: ItemListAdapter.ItemListener )
    : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var itemData: Item

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Item) {
        this.itemData = item
        itemBinding.tvTitle.text = item.title
    }

    override fun onClick(v: View?) {
        listener.onClickItem(itemData.id)
    }

}

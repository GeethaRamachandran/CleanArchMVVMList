package com.example.cleanarchmvvmlist.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchmvvmlist.databinding.ItemBinding
import com.example.cleanarchmvvmlist.domain.model.Item

class ItemListAdapter(private val listener: ItemListener) : RecyclerView.Adapter<ItemListViewHolder>()  {
    interface ItemListener {
        fun onClickItem(itemId:Int)

    }

    private var items = mutableListOf<Item>()

    //Setting Item to the adapter
    fun setItems(items: MutableList<Item>) {
        this.items=items
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

    private lateinit var itemDtoData: Item

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(items: Item) {
        this.itemDtoData = items
        itemBinding.tvTitle.text = items.title
    }

    //We can get clickedItem
    override fun onClick(v: View?) {
        listener.onClickItem(itemDtoData.id)
    }

}

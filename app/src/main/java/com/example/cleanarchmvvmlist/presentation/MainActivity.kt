package com.example.cleanarchmvvmlist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchmvvmlist.R
import com.example.cleanarchmvvmlist.common.Resource
import com.example.cleanarchmvvmlist.databinding.ActivityMainBinding
import com.example.cleanarchmvvmlist.presentation.item_list.ItemListViewModel
import com.example.cleanarchmvvmlist.presentation.search_item_list.SearchItemListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemListAdapter.ItemListener {

    private lateinit var viewModelItemList: ItemListViewModel
    private lateinit var adapter: ItemListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        viewModelItemList = ViewModelProvider(this).get(ItemListViewModel::class.java)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {

        getAllList()

    }


    //setting recyclerView
    private fun setupRecyclerView() {
        adapter = ItemListAdapter(this)
        binding!!.rvItem.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        binding!!.rvItem.adapter = adapter
    }

    override fun onClickItem(itemId: Int) {
        //To something by item click
        Toast.makeText(this, "Item Id: " + itemId, Toast.LENGTH_SHORT).show()

    }


    //Inflating directly the menu since viewbinding does not supports
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val item = menu?.findItem(R.id.action_search);
        val searchView = item?.actionView as SearchView
        searchView.inputType = InputType.TYPE_CLASS_NUMBER

        // search queryTextChange Listener
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(inputData: String?): Boolean {
                inputData?.let {
                    viewModelItemList.getSearchList(it.toInt())
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    if (!it.isEmpty()) {
                        viewModelItemList.getSearchList(it.toInt())
                    }else{
                        viewModelItemList.getList()
                    }
                }

                return false
            }
        })
        getAllList()
        return super.onCreateOptionsMenu(menu)
    }
    //Get All list
    private fun getAllList() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModelItemList.state.collect {
                if (it.isLoading) {
                    Log.d("Response", "isLoading: " + it.isLoading)
                    binding.tvNoData.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE

                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                }
                it.itemList?.let {

                    if (it.isEmpty() || it.isNullOrEmpty()) {
                        binding.tvNoData.visibility = View.VISIBLE
                    }
                    binding.tvNoData.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    adapter.setItems(it.toMutableList())
                }


            }

        }

    }
}

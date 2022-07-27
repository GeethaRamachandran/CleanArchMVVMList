package com.example.cleanarchmvvmlist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchmvvmlist.common.Resource
import com.example.cleanarchmvvmlist.data.dto.Item
import com.example.cleanarchmvvmlist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemListAdapter.ItemListener {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: ItemListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        getAllList()
        getSearchList()
    }

    private fun getSearchList() {
        binding!!.svItemSearchView.inputType = InputType.TYPE_CLASS_NUMBER
        binding!!.svItemSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                s?.let {

                    viewModel.getSearchItems(s.toInt()).observe(this@MainActivity, Observer {
                        when (it.status) {
                            Resource.Status.SUCCESS -> {
                                binding!!.progressBar.visibility = View.GONE
                                binding!!.rvItem.visibility = View.VISIBLE
                                Log.i("Response", "" + it.data!!.toString())
                                if (!it.data?.isNullOrEmpty()) {
                                    adapter.setItems(ArrayList(it.data!!))
                                    binding!!.tvNoData.visibility = View.GONE
                                } else {
                                    adapter.setItems(ArrayList(emptyList()))
                                    binding!!.tvNoData.visibility = View.VISIBLE
                                }
                            }
                            Resource.Status.ERROR ->
                                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT)
                                    .show()

                            Resource.Status.LOADING -> {
                                binding!!.progressBar.visibility = View.VISIBLE
                                binding!!.rvItem.visibility = View.GONE
                                binding!!.tvNoData.visibility = View.GONE
                            }
                        }
                    })
                }
                return false
            }

            override fun onQueryTextChange(inputData: String?): Boolean {
                if (inputData==""){
                    getAllList()
                    binding!!.tvNoData.visibility = View.GONE

                }
                return false
            }
        })


    }

    private fun getAllList() {

        viewModel.itemList.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding!!.progressBar.visibility = View.GONE
                    Log.i("Response", "" + it.data!!.toString())
                    if (!it.data?.isNullOrEmpty()) adapter.setItems(ArrayList(it.data!!))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding!!.progressBar.visibility = View.VISIBLE
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = ItemListAdapter(this)
        binding!!.rvItem.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        binding!!.rvItem.adapter = adapter
    }

    override fun onClickItem(itemId: Int) {
        Toast.makeText(this, "Item Id: " + itemId, Toast.LENGTH_SHORT).show()

    }
}
package com.aevshvetsov.minimalvkclient.ui.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aevshvetsov.minimalvkclient.R
import com.aevshvetsov.minimalvkclient.adapters.FeedRecyclerAdapter
import com.aevshvetsov.minimalvkclient.models.appmodels.FeedItemModel
import com.aevshvetsov.minimalvkclient.repositories.FeedRepository
import com.aevshvetsov.minimalvkclient.utils.Constants.ACCESS_TOKEN_KEY
import com.aevshvetsov.minimalvkclient.utils.Constants.PREFERENCE_FILE_NAME
import com.aevshvetsov.minimalvkclient.utils.RetrofitBuilder
import com.aevshvetsov.minimalvkclient.utils.mappers.FeedModelsMapper
import com.aevshvetsov.minimalvkclient.viewmodels.FeedViewModel
import com.vk.api.sdk.VK
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment(R.layout.fragment_feed) {
    private var isLogin: Boolean = false
    private lateinit var viewModel: FeedViewModel
    lateinit var repository: FeedRepository
    lateinit var feedAdapter: FeedRecyclerAdapter
    lateinit var feedList: List<FeedItemModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("M_FeedFragment", "TEST LOGIN:${VK.isLoggedIn()}")
        if (VK.isLoggedIn()) {
            isLogin = true
        }
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isLogin) {
            viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
            getRequest()
        }
    }

    private fun updateUI() {
        feedAdapter = FeedRecyclerAdapter()
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        with(rv_feed_fragment) {
            addItemDecoration(decoration)
            layoutManager = LinearLayoutManager(activity)
            adapter = feedAdapter
        }
        feedAdapter.submitList(feedList)
    }

    private fun getRequest() {
        val spref = activity?.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        val token = spref?.getString(ACCESS_TOKEN_KEY, "")
        RetrofitBuilder.token = token!!
        viewModel.getUsers().observe(this) {
            if (it.response.items.isNotEmpty()) {
                val testResponse = it.response.items.toString()
                Log.d("M_FeedFragment", "feed count is \n\t $testResponse")
                feedList = FeedModelsMapper(it.response).createListForUI()
                updateUI()
            }
        }
    }

}

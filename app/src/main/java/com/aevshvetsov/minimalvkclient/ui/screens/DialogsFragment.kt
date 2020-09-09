package com.aevshvetsov.minimalvkclient.ui.screens


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aevshvetsov.minimalvkclient.R
import com.aevshvetsov.minimalvkclient.adapters.DialogRecyclerAdapter
import com.aevshvetsov.minimalvkclient.models.appmodels.DialogItemModel
import com.aevshvetsov.minimalvkclient.utils.mappers.DialogModelsMapper
import com.aevshvetsov.minimalvkclient.viewmodels.DialogViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class DialogsFragment : Fragment(R.layout.fragment_dialogs) {
    lateinit var dialogsList: List<DialogItemModel>
    private val dialogViewModel by lazy {
        ViewModelProvider(this).get(DialogViewModel::class.java)
    }
    private val dialogAdapter = DialogRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogViewModel.getDialogs().observe(viewLifecycleOwner) { dialogNetworkModel ->
            val items = dialogNetworkModel.response.items
            if (items.isNotEmpty()) {
                dialogsList = DialogModelsMapper(dialogNetworkModel.response).createListForUI()
                updateUI()

            }

        }
    }

    private fun updateUI() {
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        with(rv_feed_fragment) {
            addItemDecoration(decoration)
            layoutManager = LinearLayoutManager(activity)
            adapter = dialogAdapter
        }
        dialogAdapter.submitList(dialogsList)
    }

}

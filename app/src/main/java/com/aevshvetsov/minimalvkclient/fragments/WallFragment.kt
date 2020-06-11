package com.aevshvetsov.minimalvkclient.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aevshvetsov.minimalvkclient.R
import com.aevshvetsov.minimalvkclient.utils.Constants.ACCESS_TOKEN_KEY
import com.aevshvetsov.minimalvkclient.utils.Constants.PREFERENCE_FILE_NAME
import com.aevshvetsov.minimalvkclient.viewmodels.UsersWallViewModel
import kotlinx.android.synthetic.main.users_wall_fragment.*


class WallFragment : Fragment() {
    private lateinit var viewModel: UsersWallViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_wall_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsersWallViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRequest()

    }

    private fun getRequest() {
        val spref = activity?.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        val token = spref?.getString(ACCESS_TOKEN_KEY, "")
        tv_test.text = token
    }

}

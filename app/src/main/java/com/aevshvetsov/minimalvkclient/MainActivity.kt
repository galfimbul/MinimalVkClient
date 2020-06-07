package com.aevshvetsov.minimalvkclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aevshvetsov.minimalvkclient.fragments.UsersWallFragment
import com.aevshvetsov.minimalvkclient.utils.Constants.ACCESS_TOKEN_KEY
import com.aevshvetsov.minimalvkclient.utils.Constants.PREFERENCE_FILE_NAME
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)?.getString(
                ACCESS_TOKEN_KEY,
                ""
            ).isNullOrEmpty()
        )
            VK.login(this, arrayListOf(VKScope.WALL, VKScope.PHOTOS))
        else {
            openMainFragment()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization
                Log.d("M_MainActivity", "Users token is : ${token.accessToken}")
                saveToken(token.accessToken)
                openMainFragment()
            }

            override fun onLoginFailed(errorCode: Int) {
                Log.d("M_MainActivity", "user failed authorization")
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun saveToken(accessToken: String) {
        val spref = getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        spref.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply()
    }

    private fun openMainFragment() {
        val fm = supportFragmentManager
        fm.beginTransaction()
            .replace(R.id.container, UsersWallFragment.newInstance())
            .commit()
    }

}

package com.aevshvetsov.minimalvkclient.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aevshvetsov.minimalvkclient.R
import com.aevshvetsov.minimalvkclient.utils.Constants.ACCESS_TOKEN_KEY
import com.aevshvetsov.minimalvkclient.utils.Constants.PREFERENCE_FILE_NAME
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Log.d("M_MainActivity", "vkLogin")
        if (VK.isLoggedIn()) {
            setupViews()
        } else {
            performLogin()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        menu?.findItem(R.id.menu_logout)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> {
                VK.logout()
                performLogin()
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization
                Log.d("M_MainActivity", "Users token is : ${token.accessToken}")
                saveToken(token.accessToken)
                VK.saveAccessToken(this@MainActivity, token.userId, token.accessToken, token.secret)
                setupViews()
            }

            override fun onLoginFailed(errorCode: Int) {
                Log.d("M_MainActivity", "user failed authorization")
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun performLogin() {
        VK.login(this, arrayListOf(VKScope.WALL, VKScope.MESSAGES, VKScope.PHOTOS, VKScope.FRIENDS, VKScope.OFFLINE))
    }

    private fun setupViews() {
        val topLevelDestinationIds = setOf(
            R.id.wallFragment,
            R.id.searchFragment,
            R.id.notificationsFragment,
            R.id.profileFragment,
            R.id.dialogsFragment
        )
        // Finding the Navigation Controller
        val navController = findNavController(R.id.fragNavHost)

        // Setting Navigation Controller with the BottomNavigationView
        bottomNavView.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(topLevelDestinationIds)
        setupActionBarWithNavController(navController, appBarConfiguration)
        toolbar.setTitleTextColor(Color.WHITE)
    }

    private fun saveToken(accessToken: String) {
        val spref = getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        spref.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply()
    }


}

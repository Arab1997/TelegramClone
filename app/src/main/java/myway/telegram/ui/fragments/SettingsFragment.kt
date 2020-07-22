package myway.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import myway.telegram.MainActivity
import myway.telegram.R
import myway.telegram.activities.RegisterActivity
import myway.telegram.utilits.AUTH
import myway.telegram.utilits.replaceActivity
import java.security.AuthProvider


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_actions_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {


                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
        }

        return true
    }

}
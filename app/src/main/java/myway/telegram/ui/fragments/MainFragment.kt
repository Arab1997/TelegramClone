package myway.telegram.ui.fragments

import androidx.fragment.app.Fragment
import myway.telegram.R
import myway.telegram.utilits.APP_ACTIVITY
import myway.telegram.utilits.hideKeyboard

class MainFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Telegram"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
    }

}
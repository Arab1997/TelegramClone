package myway.telegram.ui.fragments

import androidx.fragment.app.Fragment
import myway.telegram.R
import myway.telegram.utilits.APP_ACTIVITY

class ChatsFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Чаты"
    }

}
package myway.telegram.ui.fragments

import androidx.fragment.app.Fragment
import myway.telegram.MainActivity
import myway.telegram.utilits.APP_ACTIVITY

open class BaseFragment(layout: Int) : Fragment(layout) {
    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }


}

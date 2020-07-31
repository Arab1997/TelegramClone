package myway.telegram.ui.screens

import androidx.fragment.app.Fragment
import myway.telegram.utilits.APP_ACTIVITY

open class BaseFragment(layout: Int) : Fragment(layout) {
    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }


}

package myway.telegram.ui.fragments

import androidx.fragment.app.Fragment
import myway.telegram.MainActivity

open class BaseFragment(layout: Int) : Fragment(layout) {
    override fun onStart() {
        super.onStart()
        //APP_ACTIVITY.mAppDrawer.disableDrawer()
        (activity as MainActivity).mAppDrawer.disableDrawer()


    }

    override fun onStop() {
        super.onStop()
      //  APP_ACTIVITY.mAppDrawer.enableDrawer()
        (activity as MainActivity).mAppDrawer.enableDrawer()

    }

}

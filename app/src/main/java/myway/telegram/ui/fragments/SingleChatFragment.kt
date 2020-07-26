package myway.telegram.ui.fragments

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.toolbar_info.view.*
import myway.telegram.R
import myway.telegram.models.CommonModel
import myway.telegram.models.UserModel
import myway.telegram.utilits.*


class SingleChatFragment(private val contact: CommonModel) :
    BaseFragment(R.layout.fragment_single_chat) {
    private lateinit var mListenerInfoToolbar: AppValueEventListener
    private lateinit var mReceivingUser: UserModel
    private lateinit var mToolbarInfo: View
    private lateinit var mRefUser: DatabaseReference
    private lateinit var mRefMessages: DatabaseReference

    // private lateinit var mAdapter: SingleChatAdapter
    private lateinit var mRecyclerView: RecyclerView

    //    private lateinit var mMessagesListener: AppChildEventListener
    private var mCountMessages = 10
    private var mIsScrolling = false
    private var mSmoothScrollToPosition = true
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mLayoutManager: LinearLayoutManager

    // private lateinit var mAppVoiceRecorder: AppVoiceRecorder
    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<*>

    override fun onResume() {
        super.onResume()
        mToolbarInfo = APP_ACTIVITY.mToolbar.toolbar_info
        mToolbarInfo.visibility = View.VISIBLE
        mListenerInfoToolbar = AppValueEventListener {
            mReceivingUser = it.getUserModel()
            initInfoToolbar()
        }

        mRefUser = REF_DATABASE_ROOT.child(NODE_USERS).child(contact.id)
        mRefUser.addValueEventListener(mListenerInfoToolbar)
    }

    private fun initInfoToolbar() {
        if (mReceivingUser.fullname.isEmpty()){
            mToolbarInfo.toolbar_chat_fullname.text = contact.fullname
        }else    mToolbarInfo.toolbar_chat_fullname.text = mReceivingUser.fullname
        mToolbarInfo.toolbar_chat_image.downloadAndSetImage(mReceivingUser.photoUrl)

        mToolbarInfo.toolbar_chat_status.text = mReceivingUser.state
    }

    override fun onPause() {
        super.onPause()
        mToolbarInfo.visibility = View.GONE
//Утечки памяты
        mRefUser.removeEventListener(mListenerInfoToolbar)
    }

}
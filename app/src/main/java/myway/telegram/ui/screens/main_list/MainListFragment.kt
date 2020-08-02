package myway.telegram.ui.screens.main_list

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main_list.*
import myway.telegram.R
import myway.telegram.database.*
import myway.telegram.models.CommonModel
import myway.telegram.utilits.APP_ACTIVITY
import myway.telegram.utilits.AppValueEventListener
import myway.telegram.utilits.hideKeyboard

class MainListFragment : Fragment(R.layout.fragment_main_list) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainListAdapter
    private val mREfMainList = REF_DATABASE_ROOT.child(NODE_MAIN_LIST).child(CURRENT_UID)
    private val mREfUsers = REF_DATABASE_ROOT.child(NODE_USERS)
    private val mREfMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID)
    private var mListItem = listOf<CommonModel>()

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Telegram"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView = main_list_recycle_view
        mAdapter = MainListAdapter()

        // 1 request   get List
        mREfMainList.addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot ->
            //it.children.map = read all children item of list  and via map running each items init model
            mListItem = dataSnapshot.children.map { it.getCommonModel() }
            mListItem.forEach { model ->

                // 2 request  get all info it
                mREfUsers.child(model.id).addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot1 ->
                    val newModel = dataSnapshot1.getCommonModel()

                    //last item our list   items of messages list
                    // 3 request   get last Messages
                    mREfMessages.child(model.id).limitToLast(1)
                        .addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot2 ->
                            val tempList = dataSnapshot2.children.map { it.getCommonModel() }
                            newModel.lastMessage = tempList[0].text

                            if (newModel.fullname.isEmpty()){
                                newModel.fullname = newModel.phone
                            }
                            mAdapter.updateListItems(newModel)
                        })
                })
            }

        })

        mRecyclerView.adapter = mAdapter
    }

}
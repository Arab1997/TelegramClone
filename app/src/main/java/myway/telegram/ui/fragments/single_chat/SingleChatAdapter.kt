package myway.telegram.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.message_item.view.*
import myway.telegram.R
import myway.telegram.models.CommonModel
import myway.telegram.utilits.CURRENT_UID
import myway.telegram.utilits.asTime
import java.text.SimpleDateFormat
import java.util.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {
    private var mListMessagesCache = emptyList<CommonModel>()
    // private var mListMessagesCache = mutableListOf<MessageView>()
    // private var mListHolders = mutableListOf<MessageHolder>()

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        val blocUserMessage: ConstraintLayout = view.bloc_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user_message_time

        val blocReceivedMessage: ConstraintLayout = view.bloc_received_message
        val chatReceivedMessage: TextView = view.chat_received_message
        val chatReceivedMessageTime: TextView = view.chat_received_message_time

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        if (mListMessagesCache[position].from == CURRENT_UID) {
            holder.blocUserMessage.visibility = View.VISIBLE
            holder.blocReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessagesCache[position].text
            holder.chatUserMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
            //extation function  asTime()
        } else {
            holder.blocUserMessage.visibility = View.GONE
            holder.blocReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.text = mListMessagesCache[position].text
            holder.chatReceivedMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    fun setList(list: List<CommonModel>) {
        mListMessagesCache = list
        notifyDataSetChanged()
    }


    /*   override fun getItemViewType(position: Int): Int {
           return mListMessagesCache[position].getTypeView()
       }

       override fun getItemCount(): Int = mListMessagesCache.size

       override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
           (holder as MessageHolder).drawMessage(mListMessagesCache[position])
       }

       override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
           (holder as MessageHolder).onAttach(mListMessagesCache[holder.adapterPosition])
           mListHolders.add((holder as MessageHolder))
           super.onViewAttachedToWindow(holder)
       }

       override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
           (holder as MessageHolder).onDetach()
           mListHolders.remove((holder as MessageHolder))
           super.onViewDetachedFromWindow(holder)
       }

       fun addItemToBottom(
           item: MessageView,
           onSuccess: () -> Unit
       ) {
           if (!mListMessagesCache.contains(item)) {
               mListMessagesCache.add(item)
               notifyItemInserted(mListMessagesCache.size)
           }
           onSuccess()
       }

       fun addItemToTop(
           item: MessageView,
           onSuccess: () -> Unit
       ) {
           if (!mListMessagesCache.contains(item)) {
               mListMessagesCache.add(item)
               mListMessagesCache.sortBy { it.timeStamp.toString() }
               notifyItemInserted(0)
           }
           onSuccess()
       }

       fun onDestroy() {
           mListHolders.forEach {
               it.onDetach()
           }
       }*/


}



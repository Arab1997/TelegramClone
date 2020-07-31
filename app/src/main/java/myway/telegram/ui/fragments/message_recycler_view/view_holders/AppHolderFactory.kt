package myway.telegram.ui.fragments.message_recycler_view.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import myway.telegram.R
import myway.telegram.ui.fragments.message_recycler_view.views.MessageView
import myway.telegram.utilits.TYPE_MESSAGE_FILE
import myway.telegram.utilits.TYPE_MESSAGE_IMAGE

class AppHolderFactory {
    companion object{
        fun getHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder{
return when(viewType){
    MessageView.MESSAGE_IMAGE -> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item_image, parent, false)
        HolderImageMessage(view)
    }
    else ->{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item_text, parent, false)
        HolderTextMessage(view)
    }
}
        }
    }
}
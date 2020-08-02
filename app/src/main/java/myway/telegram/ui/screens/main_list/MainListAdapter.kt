package myway.telegram.ui.screens.main_list

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_list_item.view.*
import myway.telegram.R
import myway.telegram.models.CommonModel
import myway.telegram.ui.screens.single_chat.SingleChatFragment
import myway.telegram.utilits.downloadAndSetImage
import myway.telegram.utilits.replaceFragment

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.MainListHolder>() {

    private var listItem = mutableListOf<CommonModel>()

    class MainListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.main_list_item_name
        val itemLastMessage: TextView = view.main_list_last_message
        val itemPhoto: ImageView = view.main_list_item_photo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item,parent, false)

        val holder = MainListHolder(view)
        holder.itemView.setOnClickListener {
            replaceFragment(SingleChatFragment(listItem[holder.adapterPosition]))
        }
        return holder

    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: MainListHolder, position: Int) {
        holder.itemName.text = listItem[position].fullname
        holder.itemLastMessage.text = listItem[position].lastMessage
        holder.itemPhoto.downloadAndSetImage(listItem[position].photoUrl)
    }

    fun updateListItems(item:CommonModel){
        listItem.add(item)
        notifyItemInserted(listItem.size)
    }

}
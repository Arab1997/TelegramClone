package myway.telegram.ui.screens.message_recycler_view.views

data class ViewImageMessage(
    override val id: String,
    override val from: String,
    override val timeStamp: String,
    override val fileUrl: String,
    override val text: String = ""
) : MessageView {
    override fun getTypeView(): Int {
        return MessageView.MESSAGE_IMAGE  //vozvrashaet  tip nashi view  shtobi adapter ponimal  tip unego seychas adresovan
    }

    override fun equals(other: Any?): Boolean {
        return (other as MessageView).id == id
    }

}
package myway.telegram.utilits

import android.net.Uri
import android.provider.ContactsContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import myway.telegram.models.CommonModel
import myway.telegram.models.User
import java.util.ArrayList

lateinit var AUTH: FirebaseAuth
lateinit var USER: User
lateinit var CURRENT_UID: String

lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference

const val TYPE_TEXT = "text"
const val NODE_USERS = "users"
const val NODE_MESSAGES = "messages"
const val NODE_USERNAMES = "usernames"
const val NODE_PHONES = "phones"
const val NODE_PHONES_CONTACTS = "phones_contacts"
const val FOLDER_PROFILE_IMAGE = "profile_image"
const val FOLDER_FILES = "messages_files"

const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"
const val CHILD_FULLNAME = "fullname"
const val CHILD_BIO = "bio"
const val CHILD_PHOTO_URL = "photoUrl"
const val CHILD_STATE = "state"
const val CHILD_TEXT = "text"
const val CHILD_TYPE = "type"
const val CHILD_FROM = "from"
const val CHILD_TIMESTAMP = "timeStamp"
const val CHILD_FILE_URL = "fileUrl"

const val NODE_MAIN_LIST = "main_list"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    USER = User()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
}


inline fun putUrlToDatabase(url: String, crossinline function: () -> Unit) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
        .child(CHILD_PHOTO_URL).setValue(url)
        .addOnSuccessListener { function() }
        .addOnFailureListener { showToast(it.message.toString()) }

}

inline fun getUrlFromStorage(path: StorageReference, crossinline function: (url: String) -> Unit) {
    path.downloadUrl
        .addOnSuccessListener { function(it.toString()) }
        .addOnFailureListener { showToast(it.message.toString()) }
}

inline fun putImageToStorage(uri: Uri, path: StorageReference, crossinline function: () -> Unit) {
    path.putFile(uri)
        .addOnSuccessListener { function() }
        .addOnFailureListener { showToast(it.message.toString()) }
}


inline fun initUser(crossinline function: () -> Unit) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            USER = it.getValue(User::class.java) ?: User()// ? elves operator
            if (USER.username.isEmpty()) {
                USER.username = CURRENT_UID
            }
            function()
        })
}

fun initContacts() {
    if (checkPermission(READ_CONTACTS)) {
        var arrayContacts = arrayListOf<CommonModel>()
        val cursor = APP_ACTIVITY.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.let {
// read when not null
            while (it.moveToNext()) {
                val fullName =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phone =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val newModel = CommonModel()
                newModel.fullname = fullName
                newModel.phone = phone.replace(Regex("[\\s, -]"), "") //probelni yoqotish uchun
                arrayContacts.add(newModel)
            }
        }
        cursor?.close()
        updatePhonesToDB(arrayContacts)
    }
}

fun updatePhonesToDB(arrayContacts: ArrayList<CommonModel>) {
    REF_DATABASE_ROOT.child(NODE_PHONES).addListenerForSingleValueEvent(AppValueEventListener{
        it.children.forEach { snapshot ->
            arrayContacts.forEach { contact ->
                if (snapshot.key ==contact.phone){
                    REF_DATABASE_ROOT.child(NODE_PHONES_CONTACTS).child(CURRENT_UID).child(snapshot.value.toString()).child(
                        CHILD_ID).setValue(snapshot.value.toString())
                        .addOnFailureListener { showToast(it.message.toString()) }
                }
            }
        }
    })
}

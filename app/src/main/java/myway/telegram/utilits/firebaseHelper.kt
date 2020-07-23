package myway.telegram.utilits

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import myway.telegram.models.User

lateinit var AUTH: FirebaseAuth
lateinit var USER: User
lateinit var UID:String
lateinit var CURRENT_UID: String

lateinit var REF_DB_ROOT: DatabaseReference
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

fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    REF_DB_ROOT = FirebaseDatabase.getInstance().reference
    USER = User()
    UID = AUTH.currentUser?.uid.toString()
}

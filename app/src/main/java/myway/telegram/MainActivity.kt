package myway.telegram

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.hardware.camera2.params.InputConfiguration
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.core.Context
import com.theartofdev.edmodo.cropper.CropImage
import myway.telegram.activities.RegisterActivity
import myway.telegram.databinding.ActivityMainBinding
import myway.telegram.models.User
import myway.telegram.ui.fragments.ChatsFragment
import myway.telegram.ui.objects.AppDrawer
import myway.telegram.utilits.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
    lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        // setContentView(R.layout.activity_main)
        setContentView(mBinding.root)

        APP_ACTIVITY = this
        initFields()
        initFunc()
    }



    private fun initFunc() {
        if (AUTH.currentUser != null) {  //если user автвризован
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(),  false)
        } else {
            replaceActivity(RegisterActivity())
        }
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
        initFirebase()


    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
            .addListenerForSingleValueEvent(AppValueEventListener{
                USER = it.getValue(User::class.java) ?:User()// ? elves operator
            })
    }
}
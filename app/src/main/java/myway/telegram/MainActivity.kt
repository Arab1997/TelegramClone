package myway.telegram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import myway.telegram.activities.RegisterActivity
import myway.telegram.databinding.ActivityMainBinding
import myway.telegram.ui.fragments.ChatsFragment
import myway.telegram.ui.objects.AppDrawer
import myway.telegram.utilits.AUTH
import myway.telegram.utilits.replaceActivity
import myway.telegram.utilits.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        // setContentView(R.layout.activity_main)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
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
        AUTH = FirebaseAuth.getInstance()
    }

}
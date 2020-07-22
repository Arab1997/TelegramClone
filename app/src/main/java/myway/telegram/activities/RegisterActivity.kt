package myway.telegram.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import myway.telegram.R
import myway.telegram.databinding.ActivityRegisterBinding
import myway.telegram.ui.fragments.EnterPhoneNumberFragment
import myway.telegram.utilits.initFirebase
import myway.telegram.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mToolbar: Toolbar
    private lateinit var mBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
    }
    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_your_phone)

        replaceFragment(EnterPhoneNumberFragment(), false)
    }
}
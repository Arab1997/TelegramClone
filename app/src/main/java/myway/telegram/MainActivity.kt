package myway.telegram

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import myway.telegram.database.AUTH
import myway.telegram.database.initFirebase
import myway.telegram.database.initUser
import myway.telegram.databinding.ActivityMainBinding
import myway.telegram.ui.screens.main_list.MainListFragment
import myway.telegram.ui.screens.register.EnterPhoneNumberFragment
import myway.telegram.ui.objects.AppDrawer
import myway.telegram.utilits.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
    lateinit var mToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        /* Функция запускается один раз, при создании активити */
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        initFirebase()
        initUser {
            CoroutineScope(Dispatchers.IO).launch {
                initContacts()
            }
            initFields()
            initFunc()
        }

    }



    private fun initFunc() {
        /* Функция инициализирует функциональность приложения */
        setSupportActionBar(mToolbar)
        if (AUTH.currentUser != null) {
            mAppDrawer.create()
            replaceFragment(MainListFragment(), false)
        } else {
            replaceFragment(EnterPhoneNumberFragment(),false)
        }
    }

    private fun initFields() {
        /* Функция инициализирует переменные */
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer()
    }

    override fun onStart() {
        super.onStart()
        AppStates.updateState(AppStates.ONLINE)
    }

    override fun onStop() {
        super.onStop()
        AppStates.updateState(AppStates.OFFLINE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
            initContacts()
        }
    }
}

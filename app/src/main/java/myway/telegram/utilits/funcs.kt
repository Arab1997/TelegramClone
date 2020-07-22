package myway.telegram.utilits

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.view.*
import myway.telegram.MainActivity
import myway.telegram.R
import myway.telegram.activities.RegisterActivity
import myway.telegram.ui.fragments.ChatsFragment

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()

}


fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(
            R.id.dataContainer,
            fragment
        ).commit()
}

fun Fragment.replaceFragment(fragment: Fragment) {
    this.fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(
            R.id.dataContainer,
            fragment
        )?.commit()
}
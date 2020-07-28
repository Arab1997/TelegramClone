package myway.telegram.ui.fragments

import kotlinx.android.synthetic.main.fragment_change_bio.*
import myway.telegram.R
import myway.telegram.database.*
import myway.telegram.utilits.*


class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        setBioToDatabase(newBio)
    }
}


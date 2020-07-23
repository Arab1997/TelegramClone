package myway.telegram.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_change_bio.*
import myway.telegram.MainActivity
import myway.telegram.R
import myway.telegram.utilits.USER


class ChangeBioFragment : BaseFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        settings_input_bio.setText(USER.bio)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        /* Создание выпадающего меню*/
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /* Слушатель выбора пункта выпадающего меню */
        when (item.itemId) {
          //  R.id.settings_confirm_change -> changeName()
        }
        return true
    }

/*
    private fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        setBioToDatabase(newBio)
    }
*/





}


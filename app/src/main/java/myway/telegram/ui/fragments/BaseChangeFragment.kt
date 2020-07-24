package myway.telegram.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.mikepenz.materialize.util.KeyboardUtil.hideKeyboard
import myway.telegram.MainActivity
import myway.telegram.R
import myway.telegram.utilits.APP_ACTIVITY
import myway.telegram.utilits.hideKeyboard

/* Базовый фрагмент, от него наследуются фрагменты где происходит изменение данных о пользователе. */

open class BaseChangeFragment (layout:Int): Fragment(layout) {
    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        APP_ACTIVITY.mAppDrawer.disableDrawer()
        hideKeyboard()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        /* Создание выпадающего меню*/
        APP_ACTIVITY.menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /* Слушатель выбора пункта выпадающего меню */
        when (item.itemId) {
            R.id.settings_confirm_change -> change()
        }
        return true
    }

    open fun change() {

    }
}

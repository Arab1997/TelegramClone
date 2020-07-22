package myway.telegram.ui.fragments

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import myway.telegram.R
import myway.telegram.utilits.replaceFragment
import myway.telegram.utilits.showToast


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    override fun onStart() {
        super.onStart()
        register_btn_next.setOnClickListener {
            sendCode()
        }
    }

    private fun sendCode() {
        if (register_input_phone_number.text.toString().isEmpty()) {
            showToast(getString(R.string.register_toast_enter))
        }else{
            replaceFragment(EnterCodeFragment())
        }
    }

}
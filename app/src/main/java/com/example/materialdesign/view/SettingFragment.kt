package com.example.materialdesign.view

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign.AppTheme
import com.example.materialdesign.DarkTheme
import com.example.materialdesign.R
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    private val appTheme by lazy { AppTheme() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val currentTheme = appTheme.getTheme(requireContext().applicationContext)

        switch_theme.isChecked = (currentTheme == DarkTheme)

        switch_theme.setOnCheckedChangeListener { buttonView, isChecked ->

            appTheme.setTheme(requireContext().applicationContext, isChecked)
            applyAppTheme()

        }

        val spannable = SpannableString(anytextView.text)

        spannable.setSpan(
            ForegroundColorSpan(Color.BLUE),
            0, 8,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(Color.RED),
            9, 17,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        anytextView.text = spannable
    }

    private fun applyAppTheme() {
        requireActivity().recreate()
    }

    companion object {
        fun newInstance() = SettingFragment()
    }
}
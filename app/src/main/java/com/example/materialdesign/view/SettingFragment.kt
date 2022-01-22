package com.example.materialdesign.view

import android.os.Bundle
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
    }

    private fun applyAppTheme() {
        requireActivity().recreate()
    }

    companion object {
        fun newInstance() = SettingFragment()
    }
}
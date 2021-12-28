package com.example.materialdesign

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.view.ImageFragment
import com.example.materialdesign.view.SETTINGS

const val MY_THEME = "MyTheme"

class MainActivity : AppCompatActivity() {

    private lateinit var mSetting: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mSetting = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)!!

        if (savedInstanceState == null) {
            clearSetting()
        }
        val myTheme = mSetting.getInt(SETTINGS, 0)

        if (myTheme != 0) {
            // метод override setTheme почему то не отрабатывает, хотя все делаю вроде правильно
            // Поэтому решил сделать немного по - другому
            setTheme(myTheme)
            // отправим тупо во фрагмент id темы и все (через хранилище)
            val editor = mSetting.edit()
            editor.putInt(MY_THEME, myTheme)
            editor.apply()
        }
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(R.id.imageFragment, ImageFragment.newInstance())
                .commit()
        }
    }

    override fun setTheme(resId: Int) {
        when (resId) {
            2131755474 -> {
                theme.applyStyle(R.style.Theme_MaterialDesign, true)
            }
            2131755475 -> {
                theme.applyStyle(R.style.Theme_MaterialDesign_Dark, true)
            }
        }
        super.setTheme(resId)
    }

    private fun clearSetting() {
        var editor = mSetting.edit()
        editor.clear()
        editor.apply()
    }
}
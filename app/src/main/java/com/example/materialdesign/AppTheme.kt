package com.example.materialdesign

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

private const val NAME_SHARED_PREFERENCE = "APP_THEME"
private const val appThemeKey = "APP_THEME_KEY"
private const val DefaultTheme = 0
private const val DarkTheme = 1
private const val SunTheme = 2

class AppTheme {

    // получение текущей темы приложения
    fun getTheme(context: Context):Int{
        val preferences = context.getSharedPreferences(
            NAME_SHARED_PREFERENCE,
            AppCompatActivity.MODE_PRIVATE)

        return preferences.getInt(appThemeKey,DefaultTheme)

    }

    // выбор темы для сохранения
    fun saveTheme(numberTheme:Int):Int{
        return when (numberTheme){
            DarkTheme -> R.style.Theme_MaterialDesign_Dark
            SunTheme -> R.style.Theme_MaterialDesign
            else -> R.style.Theme_MaterialDesign
        }
    }

    // установка темы
    fun setTheme(context: Context,isDark:Boolean){
        if (isDark){
            savedThemePref(context, DarkTheme)
        }else{
            savedThemePref(context, SunTheme)
        }
    }

    // помещение темы в общее хранилище
    private fun savedThemePref(context: Context, themeNumber: Int) {
        val preferences =
            context.getSharedPreferences(NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE)
        preferences.edit()
            .putInt(appThemeKey, themeNumber)
            .apply()
    }

}
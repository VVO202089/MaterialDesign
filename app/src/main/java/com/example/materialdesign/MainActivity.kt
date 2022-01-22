package com.example.materialdesign

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.materialdesign.view.FragmentLesson4
import com.example.materialdesign.view.ImageFragment
import com.example.materialdesign.view.SettingFragment

class MainActivity : AppCompatActivity() {

    private val appTheme by lazy { AppTheme() }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val themeNumber = appTheme.getTheme(this)
        setTheme(appTheme.saveTheme(themeNumber))

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            // lesson2,lesson4
            //openImageFragment()

            //lesson3
            /*
        supportFragmentManager.commit {
            setReorderingAllowed(true)
                .addToBackStack(null)
            add<ImageFragmentLesson3>(R.id.imageFragment)
        }

             */
            // lesson4 эксперименты

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                    .addToBackStack(null)
                add<FragmentLesson4>(R.id.imageFragment)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.app_bar_settings -> {
                openSettingFragment()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun openImageFragment() {

        supportFragmentManager.commit {
            setReorderingAllowed(true)
                .addToBackStack(null)
            add<ImageFragment>(R.id.imageFragment)
        }

    }

    private fun openSettingFragment() {

        supportFragmentManager.commit {
            setReorderingAllowed(true).addToBackStack(null)
            replace<SettingFragment>(R.id.imageFragment)
        }

    }
}
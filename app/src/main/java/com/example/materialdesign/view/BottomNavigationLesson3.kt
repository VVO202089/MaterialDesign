package com.example.materialdesign.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.materialdesign.R
import com.example.materialdesign.viewmodel.AppState
import com.example.materialdesign.viewmodel.ImageViewModel_Lesson3
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_navigation_layout_lesson3.*
import java.util.Observer

class BottomNavigationLesson3(val imageLesson3_VM: ImageViewModel_Lesson3) :BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_navigation_layout_lesson3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // lesson 3
        bottom_navigation_view.setOnNavigationItemSelectedListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_view_mars_1 ->{
                    Toast.makeText(context, "Земля", Toast.LENGTH_SHORT).show()
                }
                R.id.bottom_view_mars_2 -> {
                    Toast.makeText(context, "Марс", Toast.LENGTH_SHORT).show()
                }
                R.id.bottom_view_mars_3 -> {
                    Toast.makeText(context, "Венера", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}
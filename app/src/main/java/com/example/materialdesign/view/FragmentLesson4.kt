package com.example.materialdesign.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import kotlinx.android.synthetic.main.fragment_test_lesson4.*

class FragmentLesson4:Fragment() {

    companion object {
        fun newInstance() = FragmentLesson4()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_test_lesson4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sw1.setOnCheckedChangeListener { buttonView, isChecked ->
            group1.visibility = if (isChecked) {
                SwitchCompat.VISIBLE
            } else {
                SwitchCompat.GONE
            }
        }
    }
}
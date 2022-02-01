package com.example.materialdesign.recyclerview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.recyclerview.model.Notes
import kotlinx.android.synthetic.main.fragment_item_notes.*

class NotesFragment:Fragment() {

    val note:Notes by lazy {
        (arguments?.getParcelable(Notes::class.simpleName))?:Notes()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        text_view_notes_name.setText(note.name)
        description_notes_view.setText(note.description)

    }

    companion object{
        fun newInstance(bundle: Bundle):NotesFragment{
            val fragment = NotesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
package com.example.materialdesign.recyclerview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.materialdesign.R
import com.example.materialdesign.recyclerview.EditNoteViewModel
import com.example.materialdesign.recyclerview.model.Notes
import kotlinx.android.synthetic.main.fragment_item_notes.*

class EditNotesFragment : Fragment() {

    val note: Notes by lazy {
        (arguments?.getParcelable(Notes::class.simpleName)) ?: Notes()
    }

    private val viewModel by viewModels<EditNoteViewModel>()

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
        initListener()
    }

    private fun initView() {

        // в зависимости от того, передан ли Bundle меняем текст кнопки
        val textButton = if (note == null) {
            "Создать"
        } else {
            "Изменить"
        }
        createModificationButton_view.setText(textButton)
        text_view_notes_name.setText(note.name)
        description_notes_view.setText(note.description)

    }

    private fun initListener() {

        createModificationButton_view.setOnClickListener {

            if (note == null){

            }else{

            }
        }
    }

    companion object {
        fun newInstance(bundle: Bundle): EditNotesFragment {
            val fragment = EditNotesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
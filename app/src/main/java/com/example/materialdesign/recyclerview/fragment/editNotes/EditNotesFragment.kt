package com.example.materialdesign.recyclerview.fragment.editNotes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.recyclerview.model.NoteDraft
import kotlinx.android.synthetic.main.fragment_item_notes.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

const val argsBundleKey = "NotesId"

class EditNotesFragment : Fragment() {

    private val viewModel by viewModel<EditNoteViewModel> {
        parametersOf(tempNoteId)
    }
    private var tempNoteId: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initView()
        initListener()
        registeredForListeners()

        viewModel.closeScreenViewModel.observe(requireActivity()) {
            parentFragmentManager.popBackStackImmediate()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {

        // в зависимости от того, передан ли Bundle меняем текст кнопки
        val textButton = if (tempNoteId == "") {
            "Создать"
        } else {
            "Изменить"
        }
        createModificationButton_view.setText(textButton)

    }

    override fun onAttach(context: Context) {

        if (tempNoteId != "") {
            tempNoteId = requireArguments().getString(argsBundleKey)
            if (tempNoteId == null) {
                tempNoteId = ""
            }
        }

        super.onAttach(context)
    }

    private fun initListener() {

        createModificationButton_view.setOnClickListener {
            viewModel.saveNote(
                draft = NoteDraft(
                    text_view_notes_name.text.toString(),
                    description_notes_view.text.toString()
                )
            )
        }
    }

    private fun registeredForListeners() {
        viewModel.fillViewWithData.observe(viewLifecycleOwner) { draft ->
            text_view_notes_name.setText(draft?.name)
            description_notes_view.setText(draft?.description)
        }
    }


    companion object {
        fun newInstance(id: String): EditNotesFragment {
            val fragment = EditNotesFragment()
            val data = Bundle()
            data.putString(argsBundleKey, id)
            return fragment
        }
    }
}
package com.example.materialdesign.recyclerview.fragment

import AGREEMENT_KEY
import ActivityCallableInterface
import DialogFragmentLesson6
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.recyclerview.RecyclerViewLesson6ViewModel
import com.example.materialdesign.recyclerview.adapter.Adapter_Lesson6
import androidx.lifecycle.Observer

class RecyclerViewFragmentlesson6 : Fragment() {

    private val viewModel by viewModels<RecyclerViewLesson6ViewModel>()

    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        Adapter_Lesson6(
            onNoteClickListener = { notes -> viewModel.onClickNote(notes) },
            onAffairClickListener = { affair -> viewModel.onClickAffair(affair) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_lesson6, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        registeredForListeners()

        observeViewModel()
        viewModel.loadData()
    }

    private fun initView(view: View) {
        viewModel.setAdapter(adapter)
        recyclerView = view.findViewById(R.id.recycler_view_sample)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.context_menu_lesson6,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.menu_create_notes_view -> {
                Toast.makeText(context, "Создать заметку", Toast.LENGTH_LONG)
            }
            R.id.menu_create_affairs_view ->{
                Toast.makeText(context, "Создать дело", Toast.LENGTH_LONG)
            }
        }

        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModel() {
        viewModel.getItems().observe(viewLifecycleOwner) { items ->
            adapter.items = items
            adapter.notifyDataSetChanged() // обновление списка

            // с этим разберемся попозже, но это сделать нужно
            /*
            val sampleDiffUtil = SampleDiffUtil(
                oldList = adapter.items,
                newList = items,
            )
            val sampleDiffResult = DiffUtil.calculateDiff(sampleDiffUtil)
            adapter.items = items
            sampleDiffResult.dispatchUpdatesTo(adapter)
             */
        }

        viewModel.getMessages().observe(viewLifecycleOwner){message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun registeredForListeners() {

        // открытие заметки
        viewModel.openNotes.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                (requireActivity() as ActivityCallableInterface).callEditionFragment(it)
            }
        })

        createDialog()
    }

    private fun createDialog() {
        viewModel.noteDelete.observe(viewLifecycleOwner) { dialog ->
            dialog.show(parentFragmentManager, "ОК")
            parentFragmentManager.setFragmentResultListener(DialogFragmentLesson6::class.simpleName!!,
                requireActivity(),
                { requestKey, result ->
                    viewModel.deleteNoteInRepos(result, AGREEMENT_KEY)
                })
        }
    }

}
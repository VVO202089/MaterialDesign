package com.example.materialdesign.recyclerview.fragment.listNotesAndAffair

import AGREEMENT_KEY
import DialogFragmentLesson6
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.MainActivity
import com.example.materialdesign.R
import com.example.materialdesign.recyclerview.adapter.Adapter_Lesson6
import com.example.materialdesign.recyclerview.diffUtil.DiffCallback
import com.example.materialdesign.recyclerview.myInterface.ActivityCallableInterface
import kotlinx.android.synthetic.main.fragment_recycler_view_lesson6.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RecyclerViewFragment : Fragment() {

   private val viewModel by sharedViewModel<RecyclerViewViewModel>()

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

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_recycler_view_lesson6, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        registeredForListeners()
        observeViewModel()
        //viewModel.getItems()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun initView(view: View) {
        viewModel.setAdapter(adapter)
        recyclerView = view.findViewById(R.id.recycler_view_sample)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        (requireActivity() as? MainActivity)?.setSupportActionBar(toolbar)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.context_menu_recycler_lesson6,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.menu_create_notes_view -> {
                (requireActivity() as ActivityCallableInterface).callEditionFragment("")
            }
            R.id.menu_create_affairs_view ->{

            }
        }

        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModel() {
        viewModel.itemsLiveData.observe(viewLifecycleOwner) { items ->
            //adapter.items = items
            //adapter.notifyDataSetChanged() // обновление списка

            val sampleDiffUtil = DiffCallback(
                oldList = adapter.items,
                newList = items,
            )
            val sampleDiffResult = DiffUtil.calculateDiff(sampleDiffUtil)
            adapter.items = items
            sampleDiffResult.dispatchUpdatesTo(adapter)

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
            parentFragmentManager.setFragmentResultListener(
                DialogFragmentLesson6::class.simpleName!!,
                requireActivity(),
                { requestKey, result ->
                    viewModel.deleteNoteInRepos(result, AGREEMENT_KEY)
                })
        }
    }

}
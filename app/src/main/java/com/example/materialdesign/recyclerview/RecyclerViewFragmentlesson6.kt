package com.example.materialdesign.recyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.recyclerview.adapter.Adapter_Lesson6

class RecyclerViewFragmentlesson6 : Fragment() {

    private val viewModel by viewModels<RecyclerViewLesson6ViewModel>()

    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        Adapter_Lesson6(
            onPlanetClickListener = { planet -> viewModel.onClickPlanet(planet) },
            onStarClickListener = { star -> viewModel.onClickStar(star) }
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

        recyclerView = view.findViewById(R.id.recycler_view_sample)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        observeViewModel()
        viewModel.loadData()
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
}
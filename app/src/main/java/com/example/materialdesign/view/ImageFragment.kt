package com.example.materialdesign.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.materialdesign.MY_THEME
import com.example.materialdesign.MainActivity
import com.example.materialdesign.R
import com.example.materialdesign.viewmodel.AppState
import com.example.materialdesign.viewmodel.EveryDayImageViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import geekbarains.material.ui.chips.ChipsFragment
import kotlinx.android.synthetic.main.daily_image_fragment.*

const val SETTINGS = "SETTINGS"

class ImageFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    //private var _binding: DailyImageFragmentBinding? = null
    //private val binding get() = _binding
    private lateinit var mSettting: SharedPreferences
    private val everyDayVM by lazy {
        ViewModelProvider(this).get(EveryDayImageViewModel::class.java)
    }

    companion object {
        fun newInstance() = ImageFragment()
        private var isMain = true

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        everyDayVM.getData()
            .observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.daily_image_fragment, container, false)

        //_binding = DailyImageFragmentBinding.inflate(inflater, container, false)
        // val root = binding!!.root

        //return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
/*
        val bundle = arguments

        if (bundle != null){
            themeApp = bundle.getInt("Theme")
        }
*/
        mSettting = requireActivity().getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)!!

        input_layout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }
        switch_theme.setOnCheckedChangeListener { buttonView, isChecked ->

            val theme = mSettting.getInt(MY_THEME, 0)

            if (isChecked && theme != 2131755475) {
                val editor = mSettting.edit()
                editor.putInt(SETTINGS, R.style.Theme_MaterialDesign_Dark)
                editor.apply()
                activity?.recreate()
            } else if (!isChecked) {
                val editor = mSettting.edit()
                editor.putInt(SETTINGS, R.style.Theme_MaterialDesign)
                editor.apply()
                activity?.recreate()
            }
        }
        setBottomAppBar(view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> toast("Избранное")
            R.id.app_bar_settings -> activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.bottom_sheet_container, ChipsFragment())?.addToBackStack(null)?.commit()
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        //_binding = null
    }


    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun renderData(appState: AppState?) {

        when (appState) {

            is AppState.Error -> {
            }
            is AppState.Loading -> {

            }
            is AppState.Success -> {
                val serverResponseData = appState.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    // выводим сообщение
                    toast("Пустая ссылка")
                } else {
                    image_view_nasa_image.load(url) {
                        viewLifecycleOwner
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                }
            }

        }

    }

    private fun toast(text: String?) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
        fab.setOnClickListener {
            if (isMain) {
                isMain = false
                bottom_app_bar.navigationIcon = null
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_back_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                bottom_app_bar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_plus_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }
}
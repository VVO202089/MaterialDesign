package com.example.materialdesign.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.materialdesign.R
import com.example.materialdesign.viewmodel.AppState
import com.example.materialdesign.viewmodel.ImageViewModel_Lesson3
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_navigation_layout_lesson3.*
import kotlinx.android.synthetic.main.daily_image_fragment.*

class ImageFragmentLesson3:Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    //private lateinit var mSettting: SharedPreferences
    private val imageLesson3 by lazy {
        ViewModelProvider(this).get(ImageViewModel_Lesson3::class.java)
    }

    companion object {
        fun newInstance() = ImageFragmentLesson3()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageLesson3.getData("Earth")
            .observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.image_fragment_lesson3, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_navigation_view.setOnNavigationItemReselectedListener {

            when(it.itemId){
                R.id.bottom_view_earth ->{
                    imageLesson3.getData("Earth")
                         .observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
                    Toast.makeText(context, "Земля", Toast.LENGTH_SHORT).show()
                }
                R.id.bottom_view_mars -> {
                    imageLesson3.getData("Mars")
                        .observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
                    Toast.makeText(context, "Марс", Toast.LENGTH_SHORT).show()
                }
                R.id.bottom_view_venus -> {
                    imageLesson3.getData("Venus")
                        .observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
                    Toast.makeText(context, "Венера", Toast.LENGTH_SHORT).show()
                }
            }

        }
        //setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))

        //mSettting = requireActivity().getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)!!
        /*
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
                */
        //setBottomAppBar(view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar_lesson3, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_settings -> toast("Настройки")
            android.R.id.home -> {
                activity?.let {
                    //BottomNavigationLesson3(imageLesson3).show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun renderData(appState: AppState?) {

        when (appState) {

            is AppState.Error -> {
               toast(appState.error.message.toString())
            }
            is AppState.Loading -> {
                toast("Идет загрузка")
            }

            is AppState.Success_Image -> {
                val serverResponseData = appState.serverResponseData.get(0)
                val url = serverResponseData.photos?.img_src
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
        if (context!= null){
            Toast.makeText(context, text, Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.BOTTOM, 0, 250)
                show()
            }
        }
    }

    /*
    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
        fab.setOnClickListener {
            if (ImageFragment.isMain) {
                ImageFragment.isMain = false
                bottom_app_bar.navigationIcon = null
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_back_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                ImageFragment.isMain = true
                bottom_app_bar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_plus_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

     */
}
package com.example.materialdesign.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.materialdesign.R
import com.example.materialdesign.viewmodel.AppState
import com.example.materialdesign.viewmodel.ImageViewModel_Lesson3
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.image_fragment_lesson3.*

class ImageFragmentLesson3 : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    companion object{
        fun newInstance() = ImageFragmentLesson3()
    }

    private val imageLesson3ViewModel by lazy {
        ViewModelProvider(this).get(ImageViewModel_Lesson3::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageLesson3ViewModel.getData(-1)
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

            when (it.itemId) {
                R.id.bottom_view_mars_1 -> {
                    imageLesson3ViewModel.getData(-1)
                        .observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
                }
                R.id.bottom_view_mars_2 -> {
                    imageLesson3ViewModel.getData(-2)
                        .observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
                }
                R.id.bottom_view_mars_3 -> {
                    imageLesson3ViewModel.getData(-3)
                        .observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_settings -> {
                childFragmentManager.commit {
                    setReorderingAllowed(true).addToBackStack(null)
                    replace<SettingFragment>(R.id.imageFragment)
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
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
                Toast.makeText(activity, appState.error.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
            is AppState.Loading -> {
                Toast.makeText(activity, "Идет загрузка", Toast.LENGTH_SHORT).show()
            }

            is AppState.Success_Image_Mars -> {
                val arrPhotos = appState.serverResponseData.photos
                // в ответ получаем массив "PhotosDataResponse", берем 1 запись
                if (arrPhotos.size > 0) {
                    val photos = arrPhotos.get(0)
                    val url = photos.imgSrc
                    if (url.isNullOrEmpty()) {
                        // выводим сообщение
                        Toast.makeText(activity, "Пустая ссылка", Toast.LENGTH_SHORT).show()
                    } else {
                        image_view.load(url) {
                            viewLifecycleOwner
                            error(R.drawable.ic_load_error_vector)
                            placeholder(R.drawable.ic_no_photo_vector)
                        }
                    }
                    Toast.makeText(activity, "Готово", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}